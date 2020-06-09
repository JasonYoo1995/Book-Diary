package ku.opensrcsw.book_diary;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebServer {
    String clientId, clientSecret;
    WebServer(){
        clientId = "zDiIbNcskPvwfuOy9aoI"; //애플리케이션 클라이언트 아이디값"
        clientSecret = "stC9wTRrze"; //애플리케이션 클라이언트 시크릿값"
    }

    private String cleanParse(Object string){
        String temp = String.valueOf(string);
        String temp2 = temp.replace("<b>","");
        String temp3 = temp2.replace("</b>","");
        String result;
        if(temp3.indexOf("(")!=-1){
            result = temp3.substring(0, temp3.indexOf("("));
        }
        else result = temp3;
        return result;
    }

    private ArrayList<Book> parse(String json){
        ArrayList<Book> books = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
            JSONArray infoArray = (JSONArray) jsonObject.get("items");
            for (int i=0; i<7 && i<infoArray.size(); i++){
                JSONObject itemObject = (JSONObject)infoArray.get(i);
                books.add(new Book(null, cleanParse(itemObject.get("title")), cleanParse(itemObject.get("author"))));
            }
        }
        catch(Exception e){
            System.out.println("parse error");
        }

        return books;
    }

    public ArrayList<Book> search(String title){
        String text = null;
        try {
            text = URLEncoder.encode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/book?query="+text;    // json 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        return parse(responseBody);
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}