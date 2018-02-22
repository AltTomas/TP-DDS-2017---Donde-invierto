package controllers;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.velocity.VelocityContext;

import dominio.Empresa;
import services.EmpresaServices;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.RenderUtil;

public class EmpresaController {

	String layout = "templates/layoutLogged.vtl";
	Map<String, String> model = new HashMap<String, String>();

	public EmpresaController(final EmpresaServices empresaService) {

		before("/empresas/*", (req, res) -> {

			if (req.cookie("lgwapp.adb") == null) {
				res.redirect("/");
			}

			else {

				String user = req.cookie("lgwapp.adb");

				model.put("usuario", user);

			}

		});

		get("/empresas/buscar", (req, res) -> {

			VelocityContext context = new VelocityContext();

			String result = new RenderUtil().getTempRes("templates/buscarEmpresa.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
		});

		get("/empresas", (req, res) -> {

			VelocityContext context = new VelocityContext();
			String empresa = req.queryParams("empresa");
			String all = req.queryParams("all");

			if (all != null) {
				if (all.equals("yes")) {

					context.put("empresaList", empresaService.getAllEmpresas());
				}
			} else {
				if (empresa != null) {

					List<Empresa> emp = empresaService.getEmpresa(empresa);

					if (emp == null) {
						context.put("empresaList", "error");
					}

					else {
						context.put("empresaList", emp);
					}

				}
			}

			String result = new RenderUtil().getTempRes("templates/empresa.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
		});

		get("/empresas/ingresar", (req, res) -> {

			VelocityContext context = new VelocityContext();

			String result = new RenderUtil().getTempRes("templates/ingresarEmpresa.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

		});

		post("/empresas/ingresar", (req, res) -> {

			String empresa = req.queryParams("nombre");

			empresaService.createEmpresa(empresa);

			res.status(201);
			res.redirect("/empresas/ingresar");

			return empresa;

		});

		get("/empresas/:empresa/cuentas", (req, res) -> {

			VelocityContext context = new VelocityContext();

			String empresa = req.params("empresa");

			List<Empresa> emp = empresaService.getEmpresa(empresa.substring(1));
					
			if (emp == null | emp.get(0).getCuentas().isEmpty()) {
				context.put("empresaList", "error");
			}

			else {
				context.put("empresa", empresa.substring(1));
				context.put("listaCuentas", emp.get(0).getCuentas());
			}

			String result = new RenderUtil().getTempRes("templates/empresaCuenta.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
		});

	}
}
