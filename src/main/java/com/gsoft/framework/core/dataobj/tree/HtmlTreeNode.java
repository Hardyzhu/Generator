package com.gsoft.framework.core.dataobj.tree;

import com.gsoft.framework.core.dataobj.Domain;
import com.gsoft.framework.util.PropertyUtils;
import com.gsoft.framework.util.StringUtils;

import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

public class HtmlTreeNode extends AbstractTreeNode implements TreeNode{
  private static final long serialVersionUID = 1879870009832140690L;
  private String src;
  private String href;
  private String tooltips;
  private String check;
  private Domain domain;
  private boolean expanded;
  private String icon;

  public HtmlTreeNode(String id, String text){
    super(id, text);
  }

  public HtmlTreeNode(String id, String text, TreeNodeConfig treeNodeConfig){
    super(id, text);
    if (treeNodeConfig == null) return; treeNodeConfig.render(this);
  }

  public HtmlTreeNode(Domain domain){
    this(domain, null);
  }

  public HtmlTreeNode(Domain domain, TreeNodeConfig treeNodeConfig){
    super(null, null);
    Assert.notNull(domain, "domain对象不能为空！");

    injectFromDomain(domain);
    this.domain = domain;
    if (treeNodeConfig == null) return; treeNodeConfig.render(this);
  }

  public String getSrc(){
    return this.src;
  }

  public void setSrc(String src){
    this.src = src;
  }

  public String getTooltips(){
    return this.tooltips;
  }

  public void setTooltips(String tooltips){
    this.tooltips = tooltips;
  }

  public String getCheck(){
    return this.check;
  }

  public void setCheck(String check){
    this.check = check;
  }

  public String getHref() {
    return this.href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private void injectFromDomain(Domain domain){
    Map values = TreeUtils.getValueMapFromDomain(domain);
    if (!values.containsKey("id")) {
      values.put("id", domain.toString());
    }

    Set<String> attributes = values.keySet();
    for (String attribute : attributes)
      setTreeAttributeValue(attribute, values.get(attribute));
  }

  @SuppressWarnings("rawtypes")
  private void setTreeAttributeValue(String attribute, Object value){
    if (value == null) value = "";
    if (attribute.equals("id")) {
      setId(value.toString());
    } else if (attribute.equals("parent")) {
      if ((value != null) && (value instanceof Domain)) {
        Map parentValues = TreeUtils.getValueMapFromDomain((Domain)value);

        Object parentIdObject = parentValues.get("id");
        if (parentIdObject != null)
          setParentId(parentIdObject.toString());
      }
    }
    else if (attribute.equals("parentId")) {
      setParentId(value.toString());
    } else if (attribute.equals("code")) {
      setCode(value.toString());
    } else if (attribute.equals("text")) {
      setText(value.toString());
    } else if (attribute.equals("group")) {
      setGroup(value.toString());
    } else if (attribute.equals("src")) {
      setSrc(value.toString());
    } else if (attribute.equals("href")) {
      setHref(value.toString()); } else {
      if ((!attribute.equals("num")) || 
        (!(value instanceof Integer))) return;
      setNum(((Integer)value).intValue());
    }
  }

  public String toHtml(boolean isLast, boolean isCheck){
    StringBuffer htmls = new StringBuffer();
    if (this.id == null) return "";
    String htmlText = (this.text == null) ? this.id : this.text;
    String htmlTooltips = (this.tooltips == null) ? htmlText : this.tooltips;
    StringBuffer treeNodeClasses = new StringBuffer("treeNode");
    String treeTriggerHtml = ""; String nodeHref = this.href;

    if (this.group != null) {
      treeNodeClasses.append(" " + this.group);
    }

    if (this.icon != null) {
      treeNodeClasses.append(" use-icon");
    }

    if (isCheck) {
      this.check = "check";
    }

    if (StringUtils.isEmpty(this.href)) {
      this.href = "javascript:void(0)";
    }

    if (this.check != null) {
      treeNodeClasses.append(" check");
      if (!this.check.equals("check")) {
        treeNodeClasses.append(" " + this.check);
      }
    }

    if (isLast) {
      treeNodeClasses.append(" last");
    }

    if ((this.children.size() > 0) || (this.src != null)) {
      treeNodeClasses.append(" expandable");
      if (isLast) {
        treeNodeClasses.append(" lastExpandable");
      }
      treeTriggerHtml = "<div class=\"tree-trigger\"></div>";
      if (this.expanded) {
        treeNodeClasses.append(" expanded");
      }
    }

    htmls.append("<li ");
    if (this.src != null) {
      htmls.append(" src=\"" + this.src + "\" ");
    }
    if (this.code != null) {
      htmls.append(" code=\"" + this.code + "\" ");
    }
    htmls.append(" class=\"" + treeNodeClasses.toString() + "\" id=\"" + this.id + "\">");
    htmls.append(treeTriggerHtml);

    htmls.append("<span");
    if (StringUtils.isNotEmpty(this.code)) {
      htmls.append(" id=\"" + this.code + "\"");
    }
    htmls.append(" title=\"" + htmlTooltips + "\" class=\"tree-span " + treeNodeClasses.toString() + "\">");

    if (this.icon != null) {
      htmls.append("<span class=\"youi-icon icon-" + this.icon + "\"></span>");
    }

    htmls.append("<a");

    if ((getDomain() != null) && (StringUtils.isNotEmpty(this.href))) {
      Object target = PropertyUtils.getSimplePropertyValue(getDomain(), "target");
      if (("_blank".equals(target)) || ("subpage".equals(target))) {
        htmls.append(" target=\"" + target + "\"");
      }

    }

    if (StringUtils.isNotEmpty(nodeHref)) {
      htmls.append(" href=\"" + nodeHref + "\"");
    }

    htmls.append(" class=\"tree-a page-link\" >");
    htmls.append(htmlText);
    htmls.append("</a></span>");

    htmls.append(childrenHtml(isCheck));
    htmls.append("</li>");
    return htmls.toString();
  }

  private String childrenHtml(boolean isCheck){
    StringBuffer htmls = new StringBuffer();
    if ((this.children != null) && (this.children.size() > 0))
    {
      htmls.append("<ul ");
      if (!this.expanded) {
        htmls.append(" style=\"display:none;\" ");
      }
      htmls.append(">");
      int index = 0;
      for (TreeNode child : this.children) {
        boolean isLastChild = ++index == this.children.size();
        htmls.append(((HtmlTreeNode)child).toHtml(isLastChild, isCheck));
      }
      htmls.append("</ul>");
    }
    return htmls.toString();
  }

  public Domain getDomain(){
    return this.domain;
  }

  public void setDomain(Domain domain){
    this.domain = domain;
  }

  public String toString() {
    return toHtml(true, false);
  }

  public boolean getExpanded() {
    return this.expanded;
  }

  public void setExpanded(boolean expanded) {
    this.expanded = expanded;
  }

  public String getIcon() {
    return this.icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}
