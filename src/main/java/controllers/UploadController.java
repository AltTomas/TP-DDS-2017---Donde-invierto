package controllers;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import dominio.Cuenta;
import dominio.Empresa;
import dominio.Indicador;
import dominio.Metodologia;
import services.EmpresaServices;
import services.IndicadorServices;
import services.MetodologiaServices;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.FileToStringReader;
import util.JSONLoader;
import util.RenderUtil;

public class UploadController {

	String layout = "templates/layoutLogged.vtl";
	Map<String, String> model = new HashMap<String, String>();
	public EmpresaServices empresaService;
	public IndicadorServices indicadorService;
	public MetodologiaServices metodologiaService;
	
	public UploadController() {
		
		// Inicializar servicios.
		this.empresaService = new EmpresaServices();
		this.indicadorService = new IndicadorServices();
		this.metodologiaService = new MetodologiaServices(); 
		
		before("/upload/*", (req, res) -> {

			if (req.cookie("lgwapp.adb") == null) {
				res.redirect("/");
			}

			else {

				String user = req.cookie("lgwapp.adb");

				model.put("usuario", user);

			}

		});

		get("/upload", (req, res) -> {

			VelocityContext context = new VelocityContext();

			String result = new RenderUtil().getTempRes("templates/upload.vtl", context);

			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
		});

		post("/upload", (req, res) -> {

			VelocityContext context = new VelocityContext();

			String file = req.queryParams("file");
			String opcion = req.queryParams("radio");

			if (file == null) {

				context.put("nofile", true);

				String result = new RenderUtil().getTempRes("templates/uploadOk.vtl", context);
				model.put("template", result);

				return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
			}

			if (opcion.equals("metodologia")) {

				int resp = uploadMetodologia(file);

				if (resp == 2) {
					context.put("subido", true);
				} else if (resp == 1) {
					context.put("errorfile", true);
				} else if (resp == 0) {

					context.put("vacio", true);
				}

			}

			else if (opcion.equals("indicador")) {

				int resp = uploadIndicador(file);

				if (resp == 2) {
					context.put("subido", true);
				} else if (resp == 1) {
					context.put("errorfile", true);
				} else if (resp == 0) {

					context.put("vacio", true);
				}
			}

			else if (opcion.equals("empresa")) {

				int resp = uploadEmpresa(file);

				if (resp == 2) {
					context.put("subido", true);
				} else if (resp == 1) {
					context.put("errorfile", true);
				} else if (resp == 0) {

					context.put("vacio", true);
				}

			}

			String result = new RenderUtil().getTempRes("templates/uploadOk.vtl", context);
			model.put("template", result);

			return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
		});

	}

	public int uploadEmpresa(String filePath) {

		ArrayList<Empresa> empresas = null;
		List<Empresa> empresasC = this.empresaService.getAllEmpresas();
		Boolean existe = false;
		Empresa newEmpresa = null;

		FileToStringReader reader = new FileToStringReader();
		JSONLoader loader = new JSONLoader(filePath, reader);

		try {
			empresas = loader.GetEmpresasFromJSONArray();
		} catch (Exception e) {
			return 1;
		}

		if (empresas.isEmpty()) {

			return 0;
		}

		for (int i = 0; i < empresas.size(); i++) {

			Empresa empresa = empresas.get(i);

			for (int j = 0; j < empresasC.size(); j++) {

				if (empresa.getNombre().toUpperCase().equals(empresasC.get(j).getNombre())) {

					existe = true;
				}

			}

			if (existe) {
				newEmpresa = empresa;
				existe = false;
			} else {
				newEmpresa = this.empresaService.createEmpresa(empresa.getNombre(), empresa.getAntiguedad());
			}

			uploadCuentas(newEmpresa, empresa.getCuentas());
		}

		return 2;

	}

	public Boolean uploadCuentas(Empresa empresa, List<Cuenta> cuentas) {

		Empresa empresaC = new EmpresaServices().getEmpresa(empresa.getNombre()).get(0);

		List<Cuenta> cuentasC = empresaC.getCuentas();
		Boolean existe = false;

		if (cuentasC.isEmpty()) {

			for (int i = 0; i < cuentas.size(); i++) {
			  this.empresaService.addCuenta(empresaC, cuentas.get(i));
			}

			return true;
		}
		else {

			for (int i = 0; i < cuentas.size(); i++) {

				Cuenta cuenta = cuentas.get(i);

				for (int j = 0; j < cuentasC.size(); j++) {

					Cuenta cuentaC = cuentasC.get(j);

					if ((cuenta.getNombre().equals(cuentaC.getNombre())
							& cuenta.getPeriodo().equals(cuentaC.getPeriodo()))) {

						existe = true;
					}
				}
				if (!existe) {
					this.empresaService.addCuenta(empresaC, cuenta);
					existe = false;
				}

			}

			return true;

		}

	}

	public int uploadIndicador(String filePath) {

		ArrayList<Indicador> indicadores = null;
		FileToStringReader reader = new FileToStringReader();
		JSONLoader loader = new JSONLoader(filePath, reader);
		List<Indicador> indicadoresC = new IndicadorServices().getAllIndicadores();
		Boolean existe = false;

		try {
			indicadores = loader.IndicadoresFromJSONFile();
		} catch (Exception e) {
			return 1;
		}

		if (indicadores.isEmpty()) {

			return 0;
		}

		for (int i = 0; i < indicadores.size(); i++) {

			Indicador indicador = indicadores.get(i);

			for (int j = 0; j < indicadoresC.size(); j++) {

				if (indicador.getNombre().toUpperCase().equals(indicadoresC.get(j).getNombre())) {

					existe = true;
				}

			}

			if (!existe) {				
				this.indicadorService.createIndicador(indicador.getNombre(), indicador.getFormula());
			}
		}

		return 2;

	}

	public int uploadMetodologia(String filePath) {

		ArrayList<Metodologia> metodologias = null;
		FileToStringReader reader = new FileToStringReader();
		JSONLoader loader = new JSONLoader(filePath, reader);
		List<Metodologia> metodologiasC = this.metodologiaService.getAllMetodologia();
		Boolean existe = false;

		try {
			metodologias = loader.MetodologiasFromJSONFile();
		} catch (Exception e) {
			return 1;
		}

		if (metodologias.isEmpty()) {

			return 0;
		}

		for (int i = 0; i < metodologias.size(); i++) {

			Metodologia metodologia = metodologias.get(i);

			for (int j = 0; j < metodologiasC.size(); j++) {

				if (metodologia.getNombre().toUpperCase().equals(metodologiasC.get(j).getNombre())) {

					existe = true;
				}

			}

			if (!existe) {
				this.metodologiaService.createMetodologia(metodologia.getNombre(), metodologia.getFormula());
			}

		}

		return 2;

	}

}