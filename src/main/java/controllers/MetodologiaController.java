package controllers;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

import dominio.Empresa;
import dominio.Metodologia;
import services.EmpresaServices;
import services.MetodologiaServices;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.Evaluador;
import util.RenderUtil;
import util.Values;

public class MetodologiaController {

	String layout = "templates/layoutLogged.vtl";
	Map<String, String> model = new HashMap<String, String>();
	EmpresaServices empresaServices;

	public MetodologiaController(final MetodologiaServices metodologiaServices) {

		this.empresaServices = new EmpresaServices();

		before("/metodologias/*", (req, res) -> {

			if (req.cookie("lgwapp.adb") == null) {
				res.redirect("/");
			}

			else {

				String user = req.cookie("lgwapp.adb");

				model.put("usuario", user);

			}

		});

		get("/metodologias/buscar", (req, res) -> {

			VelocityContext context = new VelocityContext();

			String result = new RenderUtil().getTempRes("templates/buscarMetodologia.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
		});

		get("/metodologias", (req, res) -> {

			VelocityContext context = new VelocityContext();

			String metodologia = req.queryParams("metodologia");
			String all = req.queryParams("all");

			if (all != null) {
				if (all.equals("yes")) {

					context.put("metodologiaList", metodologiaServices.getAllMetodologia());
				}
			} else {
				if (metodologia != null) {

					List<Metodologia> emp = metodologiaServices.getMetodologia(metodologia);

					if (emp == null) {
						context.put("metodologiaList", "error");
					}

					else {
						context.put("metodologiaList", emp);
					}

				}
			}

			String result = new RenderUtil().getTempRes("templates/metodologia.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
		});

		get("/metodologias/ingresar", (req, res) -> {

			VelocityContext context = new VelocityContext();

			String result = new RenderUtil().getTempRes("templates/ingresarMetodologia.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

		});

		post("/metodologias/ingresar", (req, res) -> {

			VelocityContext context = new VelocityContext();

			String metodologia = req.queryParams("metodologia");
			String formula = req.queryParams("formula");

			List<Metodologia> metodologias = metodologiaServices.getAllMetodologia();

			for (int i = 0; i < metodologias.size(); i++) {

				if (metodologias.get(i).getNombre().equals(metodologia.toUpperCase())) {
					context.put("existe", true);
					context.put("metodologia", StringUtils.capitalize(metodologia.toLowerCase()));

					String result = new RenderUtil().getTempRes("templates/ingresarMetodologiaOk.vtl", context);

					model.put("template", result);

					return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
				}
			}

			metodologiaServices.createMetodologia(metodologia, formula);
			context.put("creado", true);
			context.put("metodologia", StringUtils.capitalize(metodologia.toLowerCase()));
			res.status(201);

			String result = new RenderUtil().getTempRes("templates/ingresarMetodologiaOk.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

		});

		get("/metodologias/calcular", (req, res) -> {

			VelocityContext context = new VelocityContext();

			context.put("metodologiaList", metodologiaServices.getAllMetodologia());
			context.put("empresaList", this.empresaServices.getAllEmpresas());

			String result = new RenderUtil().getTempRes("templates/calcularMetodologia.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
		});

		get("/metodologias/valor", (req, res) -> {

			Evaluador evaluador = new Evaluador();

			VelocityContext context = new VelocityContext();

			String metodologia = req.queryParams("metodologia");
			String empresas = req.queryParams("empresa");
			String all = req.queryParams("all");

			if (all != null) {
				if (all.equals("yes")) {
					List<Empresa> empresasL = this.empresaServices.getAllEmpresas();
					String formula = metodologiaServices.getMetodologia(metodologia).get(0).getFormula();
					List<Values> finalVal = evaluador.evaluador(empresasL, formula);
					context.put("finalList", finalVal);
					context.put("metodologia", metodologia);
				}

			}

			else {

				String[] emps = empresas.split(",");

				List<Empresa> empresasL = new ArrayList<Empresa>();

				for (int i = 0; i < emps.length; i++) {

					empresasL.add(new EmpresaServices().getEmpresa(emps[i]).get(0));

				}

				List<Values> finalVal = evaluador.evaluador(empresasL, metodologia);

				context.put("finalList", finalVal);
				context.put("metodologia", metodologia);

			}

			String result = new RenderUtil().getTempRes("templates/metodologiaValor.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

		});

	}
}
