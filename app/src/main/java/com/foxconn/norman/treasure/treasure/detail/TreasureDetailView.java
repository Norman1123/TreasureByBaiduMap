package com.foxconn.norman.treasure.treasure.detail;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public interface TreasureDetailView {
    // 显示信息
    void showMessage(String msg);
    // 设置数据
    void setDetailData(List<TreasureDetailResult> list);
}
