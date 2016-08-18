package com.open.androidtvwidget.keyboard;

import android.view.KeyEvent;

/**
 * 键盘接口.
 * @author hailongqiu
 *
 */
public interface SoftKeyBoardable {
	
	/**
	 * 链接键盘事件处理.
	 * @param cb
	 */
	void setOnSoftKeyBoardListener(SoftKeyBoardListener cb);
	
	/**
	 * 设置键盘XML.
	 * @param layoutID : xml目录下的键盘ID.
	 */
	void setSkbLayout(int layoutID);
	
	/**
	 * 不设置键盘XML，手动编写键盘值.
	 * @param softSkb
	 */
	void setSoftKeyboard(SoftKeyboard softSkb);
	
	/**
	 * 设置选中的按键.
	 * @param softKey
	 * @return
	 */
	boolean setKeySelected(SoftKey softKey);
	void setDefualtSelectKey(int row, int index);
	
	/**
	 * 外部处理按键事件.
	 * @param keyCode
	 * @param event
	 * @return
	 */
	boolean onSoftKeyDown(int keyCode, KeyEvent event);
	boolean onSoftKeyUp(int keyCode, KeyEvent event);
}
