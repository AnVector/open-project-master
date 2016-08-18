package com.open.androidtvwidget.menu;

import android.graphics.drawable.Drawable;

/**
 * 菜单接口.
 * @author hailongqiu
 */
public interface OpenMenuItem {
	Drawable getIcon();
	/**
	 * 设置菜单图标.
	 */
	OpenMenuItem setIcon(Drawable icon);
	OpenMenuItem setIcon(int iconResId);
	OpenMenuItem setTitle(CharSequence title);
	OpenMenuItem setTitle(int title);
	OpenMenuItem setTextSize(int size);
	int getTextSize();
	/**
	 * 获取菜单文字内容.
	 */
	CharSequence getTitle();
	OpenSubMenu getSubMenu();
	/**
	 * 保存子菜单.
	 */
	void setSubMenu(OpenSubMenu subMenu);
	/**
	 * 判断子菜单是否存在.
	 */
	boolean hasSubMenu();
}
