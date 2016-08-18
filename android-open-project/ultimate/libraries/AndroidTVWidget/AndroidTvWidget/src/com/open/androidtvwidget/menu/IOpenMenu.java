package com.open.androidtvwidget.menu;

import android.view.animation.LayoutAnimationController;

public interface IOpenMenu {
	int NONE = 0;

	OpenMenuItem add(CharSequence title);
	OpenMenuItem add(int groupId, int itemId, int order, CharSequence title);
	OpenMenuItem add(int groupId, int itemId, int order, int titleRes);
	OpenSubMenu addSubMenu(int pos, OpenSubMenu openSubMenu);
	// https://github.com/nhaarman/ListViewAnimations 菜单动画.
	IOpenMenu setLayoutAnimation(LayoutAnimationController layoutAnimationController);
	//
	OpenMenu setGravity(int gravity);
	int getGravity();
	OpenMenu setLeftPadding(int leftPadding);
	int getLeftPadding();
	OpenMenu setTextSize(int size);
}
