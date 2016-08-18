package com.open.androidtvwidget.menu;

public interface IOpenMenuView {
	interface ItemView {
		void initialize(OpenMenuItemImpl itemData, int menuType);
	}
}
