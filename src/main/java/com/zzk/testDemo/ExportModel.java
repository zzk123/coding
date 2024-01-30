package com.zzk.testDemo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@ContentRowHeight(20)
@HeadRowHeight(25)
@ColumnWidth(25)
@Data
public class ExportModel  {

    @ExcelProperty(value = "姓名" ,index = 0)
    private String name;

    @ExcelProperty(value = "电话" ,index = 1)
    private String phone;

    @ExcelProperty(value = "联系人" ,index = 2)
    private String contactPerson;

    @ExcelProperty(value = "联系方式" ,index = 3)
    private String contactWay;
}