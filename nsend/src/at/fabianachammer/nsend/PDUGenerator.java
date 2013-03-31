package at.fabianachammer.nsend;

import java.io.File;
import java.io.IOException;

import org.xml.sax.InputSource;

import at.fabianachammer.nsend.pdu.ProtocolDataUnit;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;

public class PDUGenerator {

	private final static String PDU_PACKAGE = ProtocolDataUnit.class
			.getPackage().getName();
	private final static String PDU_ROOT_PATH = "/pdu/";

	public void generate() {

	}

	public void generateClassFromSchema(String schemaPath) {

		SchemaCompiler sc = XJC.createSchemaCompiler();

		sc.forcePackageName(PDU_PACKAGE);
		sc.parseSchema(new InputSource(schemaPath));
		S2JJAXBModel model = sc.bind();
		JCodeModel jcm = model.generateCode(null, null);

		String destinationPath = PDU_ROOT_PATH;

		try {
			jcm.build(new File(destinationPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
