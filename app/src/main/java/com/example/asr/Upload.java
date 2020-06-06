//package com.example.asr;
//
//import android.app.Activity;
//import android.os.Bundle;
//
//import com.example.testapp.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Upload extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.iat);
//    }
//}
//
//public class ImportDataFromExcel<BeanExportData> {
//    //将excel文件导入到内存中
//    private List<BeanExportData> datas;
//    public String ImportExcelData(){
//        datas = new List<BeanExportData>();
//        Workbook workbook = null;
//        String fileName ="StudentInfo.xls";
//        try {
//            workbook = Workbook.getWorkbook(new File(Environment.getExternalStorageDirectory()+"/"+fileName));
//            Sheet sheet = workbook.getSheet(0);
//            int rows = sheet.getRows();
//            int columns = sheet.getColumns();
//            //遍历excel文件的每行每列
//            for (int i=0; i < rows ;i++){
//                //遍历行
//                List<String> li = new ArrayList<>();
//                for (int j = 0 ; j < columns ; j++ ){
//                    Cell cell = sheet.getCell(j,i);
//                    String result = cell.getContents();
//                    if (i!=0){
//                        li.add(result);
//                    }
//                }
//                if (li.size()>0){
//                    datas.add(new BeanExportData(li.get(0),li.get(1),li.get(2),li.get(3)));
//                }
//                li = null;
//            }
//            Gson gson = new Gson();
//            return gson.toJson(datas);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (BiffException e) {
//            e.printStackTrace();
//        }
//        return "error";
//    }
//}