/**
 *  Base on JDK : Oracle JDK 1.8
 *  Creat By : JamesChang
 *  Creat Time: 2020-03-20 11:41
 *  File Description: 純文字檔轉碼
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

import org.mozilla.universalchardet.UniversalDetector;

public class TurnFile {

    //已處理的檔案數
    private static int trunFiles;

    //參數設定:設定轉碼目標
    private final static String trunEncode = "UTF-8";
    //參數設定:起始目錄
    private final static String startPath = "D:/Wezoomtek/ntpc/swjweb/web/jsp";
    //參數設定:篩選檔案
    private final static String pFilter = ".jsp";
    //參數設定:取代路徑
    private final static String replacePath = "D:/Wezoomtek/ntpc/swjweb/web/";
    //參數設定:目的路徑
    private final static String purposePath = "E:/Desktop/replace1";

    /** 檔案集合 */
    private static List<String> gFileList = new ArrayList<String>();

    /**
     * 執行主程式
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {


        //取得要轉檔的檔案路徑
        getPathDir(startPath,pFilter);

        //執行
        for( String fileSourceDir : gFileList ){

            String goalFileDir = fileSourceDir.replace(replacePath,purposePath);

            String code = checkEncoding(fileSourceDir);
            if(code != null){
                creatFile(goalFileDir);
                //寫入檔案內容
                convertFileCode(fileSourceDir,goalFileDir,code);
                trunFiles ++;
            }
            else{
                out.println("未轉碼檔案："+fileSourceDir);
            }

        }

        out.println("共計轉換 "+trunFiles+" 檔案");
    }


    /**
     * 建立檔案
     * @param path
     * @throws IOException
     */
    private static void creatFile(String path) throws IOException {
        String checkPath = path.substring(0,path.lastIndexOf("/"));
        File newPath = new File( checkPath );
        //判斷目錄是否存在，不存在則建立
        if(!newPath.exists()){
            newPath.mkdirs();
        }
        //判斷檔案是否存在，不存在則建立
        File newFile = new File( path );
        if(!newFile.exists()){
            newFile.createNewFile();
        }
    }

    /**
     * 取得要處理的檔案
     * @param startPath
     * @param fileFilter
     */
    private static void getPathDir(String startPath,String fileFilter){
        File topPathFile = new File( startPath );
        String[] filePathArray = topPathFile.list();
        if(filePathArray != null){
            for(int i=0 ; i< filePathArray.length ; i++){
                String filePath = startPath+"/"+ filePathArray[i];
                File file = new File( filePath);
                //判斷是否為檔案
                if(file.isFile()){
                    if( fileFilter.equals("*") || file.getPath().indexOf(fileFilter) >= 0 )
                        gFileList.add( startPath + "/" + filePathArray[i] );
                }
                else {
                    getPathDir(filePath,fileFilter);
                }
            }
        }
        else {
            gFileList.add( startPath );
        }

    }


    /**
     * 轉碼程式
     * @param fileSourceDir
     * @param goalFileDir
     * @throws IOException
     */
    private static void convertFileCode(String fileSourceDir, String goalFileDir,String code) throws IOException {

        BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(fileSourceDir), code));

        PrintWriter pw = new PrintWriter( new OutputStreamWriter(new FileOutputStream(goalFileDir), trunEncode));

        String line = br.readLine();

        //判斷有執行取代的資料1
        boolean hadReplace = false;

        while (line != null) {

            //取代資料
            if(line.toUpperCase().indexOf("BIG5") != -1){
                line = line.replace("big5","UTF-8");
                line = line.replace("BIG5","UTF-8");
                line = line.replace("Big5","UTF-8");
                line = line.replace("bIg5","UTF-8");
                line = line.replace("biG5","UTF-8");
                hadReplace = true;
            }
            else if(line.toUpperCase().indexOf("UTF-8") != -1){
                hadReplace = true;
            }

            pw.write(line+"\r\n");
            line = br.readLine();
        }
        if(!hadReplace){
            out.println("已轉碼，需人工檢視檔案："+fileSourceDir);
        }

        pw.flush();
        pw.close();

    }

    /**
     * 確認檔案編碼
     * @param filePath 檔案路徑
     * @return [String]charset:編碼格式
     * @throws IOException
     */
    private static String checkEncoding(String filePath) throws IOException {

        /* 讀取檔案 */
        FileInputStream fis = new FileInputStream(filePath);
        /* 建立分析器 */
        UniversalDetector detector = new UniversalDetector(null);

        int nread;
        byte[] buf = new byte[4];
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            /* 分析資料 */
            detector.handleData(buf, 0, nread);
        }
        fis.close();
        detector.dataEnd();
        /* 取得編碼格式 */
        String charset = detector.getDetectedCharset();
        return charset;
    }
}
