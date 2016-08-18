package com.open.androidtvwidget.keyboard;

public interface SoftKeyBoardListener {
	void onCommitText(SoftKey key);
	void onDelete(SoftKey key);
	void onBack(SoftKey key);
}
