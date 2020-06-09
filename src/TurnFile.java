
import static java.lang.System.out;
import com.jc.file.JcFile;


/**
 *  純文字檔轉碼
 *  @author   JamesChang
 *  @since   2020-03-20 11:41 OpenJDK 11
 */
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
    private final static String replacePath = "D:/Wezoomtek/ntpc/swjweb/";
    //參數設定:目的路徑
    private final static String purposePath = "E:/Desktop/swjweb/jsp";



    /**
     * 執行主程式
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {


        //取得要轉檔的檔案路徑
        JcFile.getPathDir(startPath,pFilter);

        //執行
        for( String fileSourceDir : JcFile.gFileList ){

            String goalFileDir = fileSourceDir.replace(replacePath,purposePath);

            String code = JcFile.checkEncoding(fileSourceDir);
            if(code != null){
//                if(
//                    !searchFileContent(fileSourceDir,code,"<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")
//                        &&
//                    searchFileContent(fileSourceDir,code,"<head>")
//                ){
//                    out.println(fileSourceDir);
//                    creatFile(goalFileDir);
//                    //寫入檔案內容
//                    convertFileCode(fileSourceDir,goalFileDir,code);
//                    trunFiles ++;
//                }
//                out.println(fileSourceDir);
//                out.println(goalFileDir);
//                JcFile.creatFile(goalFileDir);
//                //寫入檔案內容
//                JcFile.convertFileCode(fileSourceDir,goalFileDir,code,trunEncode);


                //僅搜尋檔案內容
                if(JcFile.searchFileContent(fileSourceDir,code,"getfileFN")){
                    out.println(fileSourceDir);
                    trunFiles ++;
                }

            }
            else{
                out.println("未轉碼檔案："+fileSourceDir);
            }

        }

        out.println("共計轉換 "+trunFiles+" 檔案");
    }
}
