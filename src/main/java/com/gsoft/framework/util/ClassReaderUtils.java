package com.gsoft.framework.util;

import com.gsoft.framework.core.dataobj.tree.HtmlTreeNode;
import java.io.IOException;
import net.sf.cglib.asm.AnnotationVisitor;
import net.sf.cglib.asm.Attribute;
import net.sf.cglib.asm.ClassReader;
import net.sf.cglib.asm.ClassVisitor;
import net.sf.cglib.asm.FieldVisitor;
import net.sf.cglib.asm.MethodVisitor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ClassReaderUtils{
  public static HtmlTreeNode getOutlineTree(String clazzName){
    HtmlTreeNode root = new HtmlTreeNode("root_" + clazzName.replace(".", "_"), "code struct");
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    try{
      Resource resource = resourceLoader.getResource("classpath:" + clazzName.replace(".", "/") + ".class");
      ClassReader classReader = new ClassReader(resource.getInputStream());
      TreeClassVisitor treeVisitor = new TreeClassVisitor();
      classReader.accept(treeVisitor, 0);
      root = treeVisitor.getTree();
      root.setId("root_" + clazzName.replace(".", "_"));
      root.setText(clazzName);
      root.setExpanded(true);
    }catch (IOException e) {
      e.printStackTrace();
    }

    return root;
  }
  public static void main(String[] args) {
	  
  }

  static class TreeClassVisitor implements ClassVisitor {
    private HtmlTreeNode tree;

    TreeClassVisitor() {
      this.tree = new HtmlTreeNode("root_class", "codestruct");
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
    	
    }

    public AnnotationVisitor visitAnnotation(String arg0, boolean arg1){
      return null;
    }

    public void visitAttribute(Attribute attribute){
    	
    }

    public void visitEnd(){
    	
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value){
      HtmlTreeNode treeNode = new HtmlTreeNode(name, name);
      treeNode.setGroup("field");
      this.tree.addChild(treeNode);
      return null;
    }

    public void visitInnerClass(String name, String outerName, String innerName, int access){
    	
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] arg4){
      HtmlTreeNode treeNode = new HtmlTreeNode(name, name);
      treeNode.setGroup("method");
      this.tree.addChild(treeNode);
      return null;
    }

    public void visitOuterClass(String owner, String name, String desc){
    	
    }

    public void visitSource(String source, String debug){
    	
    }

    public HtmlTreeNode getTree(){
      return this.tree;
    }

    public void setTree(HtmlTreeNode tree){
      this.tree = tree;
    }
  }
}
