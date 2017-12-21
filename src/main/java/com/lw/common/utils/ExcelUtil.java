package com.lw.common.utils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

 
 
/**
 * Excel文档工具类
 * */
@SuppressWarnings("serial")
public class ExcelUtil {
    private final static String SUFFIX_XLS = ".xls";
    private final static String SUFFIX_XLSX = ".xlsx";
    
//    public final static String RESIDENCE_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR+ "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "residenceModel.xlsx";
//    public final static String ENTERPRISE_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR + "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "enterpriseModel.xlsx";
//    public final static String PERSON_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR+ "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "personModel.xlsx";
//        
//    public final static String CORRECTION_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR+ "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "correctionModel.xlsx";
//    public final static String DRUG_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR+ "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "drugModel.xlsx";
//    public final static String COMMUNITY_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR+ "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "communityModel.xlsx";
//    public final static String PSYCHOSIS_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR+ "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "psychosisModel.xlsx";
//    public final static String FOCUSTEENAGER_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR+ "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "focusteenagerModel.xlsx";
//    public final static String FOCUSPETITION_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR+ "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "focuspetitionModel.xlsx";
//    public final static String CULT_MODEL = FileUtil.fileResourcePath() + FileUtil.FILE_SEPARATOR+ "excelmodel"
//        + FileUtil.FILE_SEPARATOR + "cultModel.xlsx";
    
    
    /**
     * 房屋信息列名map
     */

    public final static Map<String, String> ResidenceMap = new HashMap<String, String>() {
        {
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("房屋地址", "address");
            put("联系人姓名", "contactName");
            put("联系人身份证", "contactIdcard");
            put("联系人电话", "contactPhone");
            put("户主姓名", "householderName");
            put("户主身份证", "householderIdcard");
            put("户主联系方式", "householderPhone");
            put("是否出租", "isRentingHouseName");
            put("是否危旧房", "isDangerousName");
            put("危险等级", "dangerousLevel");
        }
    };
    
    /**
     * 用户信息列名map
     */

    public final static Map<String, String> UserMap = new HashMap<String, String>() {
        {
            put("序号", "id");
            put("昵称", "nickName");
            put("性别", "gender");
            put("头像", "avatar");
            put("简介", "note");
            put("完整度", "completeness");
        }
    };
    
    public final static Map<String, String> EnterpriseMap = new HashMap<String, String>() {
        {            
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("企业名称", "name");
            put("企业类型", "categoryName");
            put("工商注册号", "licenseNo");
            put("组织机构代码", "organizationNo");
            put("注册地址", "registAddress");
            put("企业联系方式", "phone");
            put("企业传真", "fax");
            put("员工数", "employeeNum");
            put("法人姓名", "legalPersonName");
            put("法人身份证号", "legalPersonIdcard");
            put("法人联系方式", "legalPersonPhone");
            put("治保负责人", "guardName");
            put("安保负责人", "safetyChargeName");
            put("经营范围", "businessScope");
            put("是否危化企业", "isChemistryName");
            put("危化品情况", "chemistrySituation");
            put("安全隐患类型", "hiddenDangerCategoryName");
            put("隐患描述", "hiddenDangerSituation");
            put("关注程度", "attentionDegreeName");
        }
    };
    
    public final static Map<String, String> PersonMap = new HashMap<String, String>() {
        {            
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("姓名", "name");
            put("证件类型", "certificateTypeName");
            put("证件号码", "certificateNo");
            put("人口类型", "categoryName");
            put("性别", "sexName");
            put("民族", "nationName");
            put("出生年月", "birthDateTemp");
            put("现居住地", "currentAddress");
            put("与户主关系", "householderRelationName");
            put("户籍所在地", "nativePlaceName");
            put("联系方式", "phone");
            put("职业", "jobName");
            put("工作单位", "company");
            put("政治面貌", "politicalStatusName");
            put("婚姻状况", "maritalStatusName");
            put("健康状况", "healthStatusName"); 
        }
    };
    
    public final static Map<String, String> CorrectionMap = new HashMap<String, String>() {
        {
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("姓名", "name"); 
            put("身份证号", "certificateNo");
            put("矫正类型", "correctionTypeName");
            put("原判罪名", "crimeName");
            put("原判刑期", "originalPenaltyTerm");
            put("原判起时", "originalPenaltyBeginTmp");
            put("原判止时", "originalPenaltyEndTmp");
            put("矫正刑期", "correctionTerm");
            put("矫正起时", "correctionBeginTmp");
            put("矫正止时", "correctionEndTmp");
            put("矫正编号", "correctionNo");
            put("管控人姓名", "managerName");
            put("管控人电话", "managerPhone");
            put("管控等级", "manageLevelName");
            put("主要犯罪事实", "mainCriminalFacts"); 
        }
    }; 
    
    public final static Map<String, String> DrugMap = new HashMap<String, String>() {
        {
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("姓名", "name"); 
            put("身份证号", "certificateNo");
            put("戒毒情况", "drugTreatmentSituationName");
            put("吸毒原因", "drugReasonName");
            put("毒品来源", "drugSourceName");
            put("吸毒状态", "drugStatusName");
            put("管控人姓名", "managerName");
            put("管控人电话", "managerPhone");
            put("管控等级", "manageLevelName"); 
        }
    };
    
    public final static Map<String, String> CommunitySentenceMap = new HashMap<String, String>() {
        {
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("姓名", "name"); 
            put("身份证号", "certificateNo");
            put("具体罪名", "crime");
            put("原判刑期", "originalPenaltyTerm");
            put("原判起时", "originalPenaltyBeginTmp");
            put("原判止时", "originalPenaltyEndTmp");
            put("矫正刑期", "correctionTerm");
            put("矫正起时", "correctionBeginTmp");
            put("矫正止时", "correctionEndTmp");
            put("矫正编号", "correctionNo");
            put("管控人姓名", "managerName");
            put("管控人电话", "managerPhone");
            put("管控等级", "manageLevelName");
            put("主要犯罪事实", "mainCriminalFacts"); 
        }
    };
 
    public final static Map<String, String> PsychosisMap = new HashMap<String, String>() {
        {
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("姓名", "name"); 
            put("身份证号", "certificateNo");
            put("精神病类型", "psychosisTypeName");
            put("危险程度", "dangerLevelName");
            put("患病名称", "sicknessName");
            put("治疗医院", "treatmentHospital"); 
            put("管控人姓名", "managerName");
            put("管控人电话", "managerPhone");
            put("管控等级", "manageLevelName");
            put("诊断结果", "diagnosisResult"); 
            put("治疗结果", "treatmentResult"); 
        }
    };
    
    public final static Map<String, String> FocusTeenagerMap = new HashMap<String, String>() {
        {
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("姓名", "name"); 
            put("身份证号", "certificateNo");
            put("青少年类型", "teenagerTypeName");
            put("是否犯罪", "isCrimeName");
            put("帮扶人姓名", "helperName");
            put("帮扶人电话", "helperPhone"); 
            put("监护人姓名", "managerName");
            put("监护人电话", "managerPhone");
            put("关注等级", "focusLevelName");
            put("家庭情况", "homeSituation");  
        }
    };
    
    public final static Map<String, String> FocusPetitionMap = new HashMap<String, String>() {
        {
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("姓名", "name"); 
            put("身份证号", "certificateNo");
            put("信访类型", "petitionTypeName");
            put("信访内容", "petitionDescription"); 
            put("信访结果", "petitionResult"); 
            put("管控人姓名", "managerName");
            put("管控人电话", "managerPhone");
            put("关注等级", "focusLevelName"); 
        }
    };

    public final static Map<String, String> CultMap = new HashMap<String, String>() {
        {
            put("序号", "id");
            put("所属街道", "townshipName");
            put("所属社区", "communityName");
            put("所属网格", "gridName");
            put("姓名", "name"); 
            put("身份证号", "certificateNo");
            put("邪教类型", "cultTypeName"); 
            put("开始时间", "startTimeTmp");
            put("结束时间", "endTimeTmp");
            put("介绍人", "introducer"); 
            put("管控人姓名", "managerName");
            put("管控人电话", "managerPhone");
            put("管控等级", "manageLevelName"); 
        }
    };
    /**
     * 批量导入文件解析处理
     * @param <T>
     * @throws Exception
     */
    public <T> void parseImportFile(InputStream inputStream, String fileName, List<T> modelList, 
        Class<T> modelClass, Map<String, String> columnMap)  throws Exception 
    {
        Workbook workbook = null;
        // 根据后缀，得到不同的Workbook子类，即HSSFWorkbook或XSSFWorkbook
        if (fileName.endsWith(SUFFIX_XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileName.endsWith(SUFFIX_XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet != null) {
           parseSheet(sheet, modelList, modelClass, columnMap);
        }
    }

    private <T> void parseSheet(Sheet sheet, List<T> modelList, Class<T> modelClass, Map<String, String> columnMap) throws Exception {
        Row row;
        int count = 0;
        List<Method> methods = null; // 保存需要调用的model中的方法
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            row = iterator.next();
            if (count == 0) {
                // 第一行是标题，单独处理
                methods = new ArrayList<>();
                parseRowAndFindMethod(row, methods, modelClass, columnMap);
            } else {
                // 处理其它数据行
                parseRowAndFillData(row, modelList, methods, modelClass);
            }
            count++;
        }
    }
 
    private <T> void parseRowAndFindMethod(Row row, List<Method> methods, Class<T> modelClass, Map<String, String> columnMap) throws Exception {
        List<String> rst = parseTitleRow(row);
        String methodName;

        for (Object str : rst) {
            String fieldName = columnMap.get(str);
            methodName = "set" + upperFirstLetter(fieldName);
            //Field field = modelClass.getDeclaredField(fieldName); // 获得对应的变量
            //methods.add(modelClass.getDeclaredMethod(methodName, field.getType())); // 根据变量类型和名称，得到对应的set函数 
//            Field field = modelClass.getField(fieldName); // 获得对应的变量
            Field field = modelClass.getDeclaredField(fieldName);
            methods.add(modelClass.getMethod(methodName, field.getType())); // 根据变量类型和名称，得到对应的set函数
        }
    }

    
    private <T> void parseRowAndFillData(Row row, List<T> modelList, List<Method> methods, Class<T> modelClass) throws Exception {
        // 判断是否为空行
        if (row.getCell(0) == null) {
            return;
        }
        
        List<String> rst = parseDataRow(row, methods.size());
        T obj = (T) modelClass.newInstance();
        // 避免由于Excel格式不对，导致每一行数据解析地不对
        if (methods.size() != rst.size()) {
            throw new Exception("Excel文件格式有误");
        } else {
            for (int i = 0; i < methods.size(); i++) {
                Method m = methods.get(i);
                String str = rst.get(i);
                if (Utils.judgeStr(str)) { // 如果为null或""，不处理
                    Class<?>[] paramTypes = m.getParameterTypes();
                    String typeName = paramTypes[0].getName(); // 根据函数参数类型，对rst中的内容进行处理
                    switch (typeName) {
                    case "java.math.BigDecimal":
                        m.invoke(obj, new BigDecimal(str));
                        break;
                    case "java.lang.Integer":
                        m.invoke(obj, new Integer(str));
                        break;
                    case "java.lang.Byte":
                        m.invoke(obj, new Byte(str));
                        break;
                    default:
                        m.invoke(obj, str);
                        break;
                    }
                }
            }
            modelList.add(obj);
        }
    }    

    private List<String> parseTitleRow(Row row) {
        List<String> rst = new ArrayList<>();
        Cell cell;
        Iterator<Cell> iterator = row.iterator();
        while (iterator.hasNext()) {
            cell = iterator.next();
            cell.setCellType(Cell.CELL_TYPE_STRING);
            rst.add(cell.getStringCellValue());
        }
        return rst;
    }
    
    private List<String> parseDataRow(Row row, int size) {
        List<String> rst = new ArrayList<>();
        Cell cell;
        for (int i = 0; i < size; i++) {
            cell = row.getCell(i, Row.RETURN_BLANK_AS_NULL);
            if (cell == null) {
                rst.add("");
            } else {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                rst.add(cell.getStringCellValue().trim());
            }
        }
        return rst;
    }

    /**
     * 字符串首字母转大写
     * @param str
     * @return
     */
    private String upperFirstLetter(String str) {
        String firstLetter = "" + str.charAt(0);
        return firstLetter.toUpperCase() + str.substring(1);
    }
 
//    public static void main(String[] args) throws Exception {    
//        ResidenceInfo info1 = new ResidenceInfo();
//        info1.setAddress("address5"); 
//        ResidenceInfo info2 = new ResidenceInfo();
//        info2.setAddress("address6"); 
//        List<ResidenceInfo> list = new ArrayList<>(); 
//        list.add(info1);
//        list.add(info2); 
//        
//        String modelPath = "D:\\zy7037\\Desktop\\excel最终模版\\民房数据批量导入模版.xlsx";
//        Workbook book = new ExcelUtil().batchImportFailList(modelPath, list, ResidenceInfo.class, ResidenceMap); 
//        //创建文件流     
//        OutputStream stream = new FileOutputStream("D:\\zy7037\\Desktop\\excel最终模版\\test.xlsx");    
//        //写入数据     
//        book.write(stream);    
//        //关闭文件流     
//        stream.close();       
//    }
    
    //返回未导入成功的excel
    public <T> void returnFailImportExcel(HttpServletResponse response, String modelPath, List<T> objectList, Class<T> modelClass,
        Map<String, String> columnMap)
    {
//        result.setData("D:\\lw7068\\Desktop\\新建文件夹 (4)\\富春江镇企业数据.xlsx");
//        result.setMessage("shibai",0);
//        return result;
        OutputStream outputStream = null;
        try
        {
            String fileName = "未成功导入数据" + TimeUtil.currTime();
            Workbook workbook = batchImportFailList(modelPath, objectList, modelClass, columnMap);
            excelName(response, fileName);
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                try
                {
                    outputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
        }

    }
    
    public HttpServletResponse excelName(HttpServletResponse response, String fileName) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + handleFileName(fileName) + ".xlsx"); 
        return response;
    }

    private String handleFileName(String fileName) {
        try {
            return new String(fileName.getBytes("GBK"), "ISO-8859-1");
        } catch (Exception e) {
            return "";
        }
    }
    
    public <T> Workbook batchImportFailList(String modelPath, List<T> objectList, Class<T> modelClass,
        Map<String, String> columnMap) throws Exception
    {
        //获取服务器excel模版
        File localfile= new File("D:\\lw7068\\Desktop\\新建文件夹 (4)\\富春江镇企业数据.xlsx");
        InputStream in = new FileInputStream(localfile);
        // 获取工作文档对象
        Workbook book = new XSSFWorkbook(in);
        // 获取sheet对象
        Sheet sheet = book.getSheetAt(0); 
        //第一行标题
        Row titleRow = sheet.getRow(0);
        //获取标题行中名对应的index
        Map<String, Integer> rowIndex = getTitleRowIndex(titleRow);
        //获取标题行名对应的method
        Map<Integer, Method> methodsIndex = getRowIndexAndGetMethod(titleRow, rowIndex, 
            modelClass, columnMap); 
        for (int i = 0; i < objectList.size(); i++)
        {
            //excel中列的index  和 列表中每个对象的变量值 对应
            Map<Integer, String> dataMap = getRowIndexAndData(titleRow, objectList.get(i), methodsIndex);
            //从第二行开始写入
            Row row = sheet.createRow(i+1); 
            //列表中一个对象写入一行，开始写入
            for (Map.Entry<Integer, String> entry : dataMap.entrySet())
            { 
                Cell cell = row.createCell(entry.getKey());                 
                cell.setCellValue(entry.getValue());
            } 
        }
        return book; 
    }

    private Map<String, Integer> getTitleRowIndex(Row row) {
        Map<String, Integer> rowIndex= new HashMap<String, Integer>();
        Cell cell;
        Iterator<Cell> iterator = row.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            cell = iterator.next();
            cell.setCellType(Cell.CELL_TYPE_STRING);
            rowIndex.put(cell.getStringCellValue(), index);
            index++;
        }//获取第一行列名对应index
        return rowIndex;
    }
    private <T> Map<Integer, Method> getRowIndexAndGetMethod(Row row, Map<String, Integer> titleRowIndex, Class<T> modelClass, Map<String, String> columnMap)
        throws Exception 
    {
        Map<Integer, Method> methodIndex = new HashMap<Integer, Method>();//列序号index对应的方法 
        
        for (Map.Entry<String, Integer> entry : titleRowIndex.entrySet())
        {
            String fieldName = columnMap.get(entry.getKey());            
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, modelClass);//得到对应的get函数          
            methodIndex.put(entry.getValue(), pd.getReadMethod()); 
        }
        return methodIndex;
    }
    
    
    private <T> Map<Integer, String> getRowIndexAndData(Row row, Object object, Map<Integer, Method> methodMap)
        throws Exception 
    {
        Map<Integer, String> dataMap= new HashMap<Integer, String>(); 
        for (Map.Entry<Integer, Method> entry : methodMap.entrySet())
        {
            Object invoke = entry.getValue().invoke(object);
            String string = invoke==null?"":invoke.toString();
            dataMap.put(entry.getKey(), string);
        }
        return dataMap;
    }
    
    
    /**
     * 创建excel文档，
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

//        Font f3=wb.createFont();
//        f3.setFontHeightInPoints((short) 10);
//        f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }

}
