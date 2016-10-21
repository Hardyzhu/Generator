package com.gsoft.platform.codegen.compile;

import com.gsoft.framework.core.dataobj.Domain;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.SerialVersionUID;

import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.IProblemFactory;
import org.eclipse.jdt.internal.compiler.batch.CompilationUnit;
import org.eclipse.jdt.internal.compiler.batch.FileSystem;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;
import org.springframework.util.FileCopyUtils;

public final class CompileUtils{
	private static final String javaVersion;
	public static String COMPILE_ENCODEING = "UTF-8";
	public static String COMPILE_JAVE_VERSION;

	static{
	    javaVersion = System.getProperty("java.version");
	    if (javaVersion.contains("1.7.")) {
	      COMPILE_JAVE_VERSION = "1.7";
	    }
	    else if (javaVersion.contains("1.6."))
	      COMPILE_JAVE_VERSION = "1.6";
	    else
	      COMPILE_JAVE_VERSION = "1.5";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map<Object, Object> getSettings(){
	    Map settings = new HashMap();
	    settings.put("org.eclipse.jdt.core.compiler.debug.lineNumber", "generate");
	    settings.put("org.eclipse.jdt.core.compiler.debug.sourceFile", "generate");
	    settings.put("org.eclipse.jdt.core.compiler.problem.deprecation", "ignore");
	    settings.put("org.eclipse.jdt.core.encoding", COMPILE_ENCODEING);
	
	    settings.put("org.eclipse.jdt.core.compiler.source", COMPILE_JAVE_VERSION);
	    settings.put("org.eclipse.jdt.core.compiler.codegen.targetPlatform", COMPILE_JAVE_VERSION);
	    settings.put("org.eclipse.jdt.core.compiler.compliance", COMPILE_JAVE_VERSION);
	    return settings;
	}

	public static void doCompile(String srcFile, ICompilerRequestor requestor){
	    INameEnvironment environment = new FileSystem(new String[0], new String[0], "UTF-8");
	    IErrorHandlingPolicy policy = DefaultErrorHandlingPolicies.proceedWithAllProblems();
	    CompilerOptions options = new CompilerOptions(getSettings());
	
	    IProblemFactory problemFactory = new DefaultProblemFactory(Locale.getDefault());
	
	    Compiler compiler = 
	      new Compiler(environment, 
	      policy, 
	      options, 
	      requestor, 
	      problemFactory);
	
	    char[] content = (char[])null;
	    try {
	      content = FileCopyUtils.copyToString(new FileReader(srcFile)).toCharArray();
	    }
	    catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	
	    if (content != null) {
	      ICompilationUnit compilationUnit = new CompilationUnit(content, srcFile, "UTF-8");
	      compiler.compile(new ICompilationUnit[] { compilationUnit });
	    }
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
	    String srcFilePath = "D:\\codeout\\src\\main\\java\\com\\gsoft\\esb\\buscfg\\entity\\BusAdapter.java";
	    doCompile("D:\\codeout\\src\\main\\java\\com\\gsoft\\esb\\buscfg\\entity\\BusAdapter.java", new ICompilerRequestor()
	    {
	      public void acceptResult(CompilationResult result) {
	        for (ClassFile clazzFile : result.getClassFiles())
	          try {
	            ClassPool pool = ClassPool.getDefault();
	            CtClass superclass = pool.get(Domain.class.getName());
	            CtClass ctClass = pool.makeClass(new ByteArrayInputStream(clazzFile.getBytes()));
	            ctClass.setSuperclass(superclass);
	
	            SerialVersionUID.setSerialVersionUID(ctClass);
	
	            long serialVersionUID = ((Long)ctClass.getField("serialVersionUID").getConstantValue()).longValue();
	
	            System.out.println(new String(clazzFile.fileName()) + ".class " + serialVersionUID);
	
	            File srcFile = new File("D:\\codeout\\src\\main\\java\\com\\gsoft\\esb\\buscfg\\entity\\BusAdapter.java");
	
	            byte[] bytes = FileCopyUtils.copyToByteArray(srcFile);
	
	            String srcContent = new String(bytes, CompileUtils.COMPILE_ENCODEING);
	
	            srcContent = srcContent.replace("//private static final long serialVersionUID = 1L;", 
	              "private static final long serialVersionUID = " + serialVersionUID + "L;");
	
	            FileCopyUtils.copy(srcContent.getBytes(), srcFile);
	          }
	          catch (Exception e) {
	            e.printStackTrace();
	          }
	      }
	    });
	}
}
