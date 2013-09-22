package com.loongsoft.knowledgebase.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class EasyUiTreeNode implements Serializable {
	/**
	 * 节点Id
	 */
	private String id;
	/**
	 * 节点名称
	 */
	private String text;

	/**
	 * 节点前的图标
	 */
	private String iconCls;
	/**
	 * 是否勾选
	 */
	private boolean checked = false;
	/**
	 * 节点的其它属性
	 */
	private Map<String, Object> attributes;
	/**
	 * 子节点
	 */
	private List<EasyUiTreeNode> children;

	/**
	 * 是否展开
	 */
	private String state = "open";

	public EasyUiTreeNode() {
	}

	public EasyUiTreeNode(String id, String text, String iconCls,
			boolean checked, Map<String, Object> attributes,
			List<EasyUiTreeNode> children, String state) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<EasyUiTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUiTreeNode> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
