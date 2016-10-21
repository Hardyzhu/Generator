package com.gsoft.platform.codegen.compile;

import com.gsoft.framework.core.dataobj.Domain;
import com.gsoft.platform.codegen.domain.DomainData;
import java.io.ByteArrayInputStream;
import java.io.File;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.SerialVersionUID;
import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.springframework.util.FileCopyUtils;

public class ModelCompile{
	private static final ModelCompile instance = new ModelCompile();

	public static ModelCompile getInstance(){
		return instance;
	}

	public void compile(DomainData domainData){
		final String srcFileName = domainData.getFullPath();
		ICompilerRequestor  icr = new ICompilerRequestor() {
			@Override
			public void acceptResult(CompilationResult result) {
				for (ClassFile clazzFile : result.getClassFiles()){
					 try {
					   ClassPool pool = ClassPool.getDefault();
					   CtClass superclass = pool.get(Domain.class.getName());
					   CtClass ctClass = pool
					     .makeClass(new ByteArrayInputStream(clazzFile
					     .getBytes()));
					   ctClass.setSuperclass(superclass);
					   SerialVersionUID.setSerialVersionUID(ctClass);
					
					   long serialVersionUID = ((Long)ctClass.getField(
					     "serialVersionUID").getConstantValue()).longValue();
					
					   File srcFile = new File(srcFileName);
					
					   byte[] bytes = FileCopyUtils.copyToByteArray(srcFile);
					
					   String srcContent = new String(bytes, "UTF-8");
					
					   srcContent = srcContent
					     .replace(
					     "//private static final long serialVersionUID = 1L;", 
					     "private static final long serialVersionUID = " + 
					     serialVersionUID + "L;");
					
					   FileCopyUtils.copy(srcContent.getBytes(), srcFile);
					 }catch (Exception e) {
					   e.printStackTrace();
					 }
				}
			}
		};
		CompileUtils.doCompile(srcFileName, icr);
	}
}

