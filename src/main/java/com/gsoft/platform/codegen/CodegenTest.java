
package com.gsoft.platform.codegen;


import org.dom4j.Document;

import com.gsoft.framework.util.Dom4jUtils;
import com.gsoft.platform.codegen.model.ModelFactory;
import com.gsoft.platform.codegen.pdm.Pdm2ModelXml;

/**
 * 根据PDF生成代码
 * @author maogaofei
 * @date 2016年7月25日 上午9:36:41
 */
public class CodegenTest {

    public static void main(String[] args) {
        String tablePrefix = "dm";
        //配置PDM路径
        Document doc = Dom4jUtils.saxParse("E:/mysqlpdm/deviceManage.pdm");
        Pdm2ModelXml pdm2ModelXml = new Pdm2ModelXml(doc,tablePrefix);
        Document modelXml = pdm2ModelXml.getModelDoc();
        ModelFactory.getInstance().registerModels(modelXml);
        GenerateEngine generateEngine = new GenerateEngine();
        GenerateConfig config = new GenerateConfig();
        
        //设置生成代码保存路径
        config.setSourceHome("E:/workspace/myProject/myGenerator/generator");
        String[] commonBuff = new String[]{"winphone"};
        for(int i = 0;i<commonBuff.length;i++){
        	config.setPackagePrefix("com");
        	generateEngine.generate(commonBuff[i], config);
        }
    }
}