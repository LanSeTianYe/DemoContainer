package com.sun.xiaotian.demo.test.fastjosn;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class Root {

    public static void main(String[] args) {
        Root miniProgram = JSON.parseObject("{\"authorizer_info\":{\"nick_name\":\"微信SDK Demo Special\",\"head_img\":\"http://wx.qlogo.cn/mmopen/GPy\",\"service_type_info\":{\"id\":2},\"verify_type_info\":{\"id\":0},\"user_name\":\"gh_eb5e3a772040\",\"principal_name\":\"腾讯计算机系统有限公司\",\"business_info\":{\"open_store\":0,\"open_scan\":0,\"open_pay\":0,\"open_card\":0,\"open_shake\":0},\"qrcode_url\":\"URL\",\"signature\":\"时间的水缓缓流去\",\"MiniProgramInfo\":{\"network\":{\"RequestDomain\":[\"https://www.qq.com\",\"https://www.qq.com\"],\"WsRequestDomain\":[\"wss://www.qq.com\",\"wss://www.qq.com\"],\"UploadDomain\":[\"https://www.qq.com\",\"https://www.qq.com\"],\"DownloadDomain\":[\"https://www.qq.com\",\"https://www.qq.com\"]},\"categories\":[{\"first\":\"资讯\",\"second\":\"文娱\"},{\"first\":\"工具\",\"second\":\"天气\"}],\"visit_status\":0}},\"authorization_info\":{\"authorization_appid\":\"wxf8b4f85f3a794e77\",\"func_info\":[{\"funcscope_category\":{\"id\":17}},{\"funcscope_category\":{\"id\":18}},{\"funcscope_category\":{\"id\":19}}]}}", Root.class);
        Root authorize = JSON.parseObject("{\"authorizer_info\":{\"nick_name\":\"微信SDK Demo Special\",\"head_img\":\"http://wx.qlogo.cn/mmopen/GPy\",\"service_type_info\":{\"id\":2},\"verify_type_info\":{\"id\":0},\"user_name\":\"gh_eb5e3a772040\",\"principal_name\":\"腾讯计算机系统有限公司\",\"business_info\":{\"open_store\":0,\"open_scan\":0,\"open_pay\":0,\"open_card\":0,\"open_shake\":0},\"alias\":\"paytest01\",\"qrcode_url\":\"URL\"},\"authorization_info\":{\"authorization_appid\":\"wxf8b4f85f3a794e77\",\"func_info\":[{\"funcscope_category\":{\"id\":1}},{\"funcscope_category\":{\"id\":2}},{\"funcscope_category\":{\"id\":3}}]}}", Root.class);
        System.out.println(123);
    }

    private AuthorizeInfo authorizer_info;

    private Authorization_info authorization_info;

    public void setAuthorizer_info(AuthorizeInfo authorizer_info) {
        this.authorizer_info = authorizer_info;
    }

    public AuthorizeInfo getAuthorizer_info() {
        return this.authorizer_info;
    }

    public void setAuthorization_info(Authorization_info authorization_info) {
        this.authorization_info = authorization_info;
    }

    public Authorization_info getAuthorization_info() {
        return this.authorization_info;
    }
}

class AuthorizeInfo {
    private String alias;

    private String nick_name;

    private String head_img;

    private Service_type_info service_type_info;

    private Verify_type_info verify_type_info;

    private String user_name;

    private String principal_name;

    private Business_info business_info;

    private String qrcode_url;

    private String signature;

    private MiniProgramInfo MiniProgramInfo;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public Service_type_info getService_type_info() {
        return service_type_info;
    }

    public void setService_type_info(Service_type_info service_type_info) {
        this.service_type_info = service_type_info;
    }

    public Verify_type_info getVerify_type_info() {
        return verify_type_info;
    }

    public void setVerify_type_info(Verify_type_info verify_type_info) {
        this.verify_type_info = verify_type_info;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPrincipal_name() {
        return principal_name;
    }

    public void setPrincipal_name(String principal_name) {
        this.principal_name = principal_name;
    }

    public Business_info getBusiness_info() {
        return business_info;
    }

    public void setBusiness_info(Business_info business_info) {
        this.business_info = business_info;
    }

    public String getQrcode_url() {
        return qrcode_url;
    }

    public void setQrcode_url(String qrcode_url) {
        this.qrcode_url = qrcode_url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public MiniProgramInfo getMiniProgramInfo() {
        return MiniProgramInfo;
    }

    public void setMiniProgramInfo(MiniProgramInfo miniProgramInfo) {
        MiniProgramInfo = miniProgramInfo;
    }
}

class Service_type_info {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}

class Verify_type_info {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}

class Business_info {
    private int open_store;

    private int open_scan;

    private int open_pay;

    private int open_card;

    private int open_shake;

    public void setOpen_store(int open_store) {
        this.open_store = open_store;
    }

    public int getOpen_store() {
        return this.open_store;
    }

    public void setOpen_scan(int open_scan) {
        this.open_scan = open_scan;
    }

    public int getOpen_scan() {
        return this.open_scan;
    }

    public void setOpen_pay(int open_pay) {
        this.open_pay = open_pay;
    }

    public int getOpen_pay() {
        return this.open_pay;
    }

    public void setOpen_card(int open_card) {
        this.open_card = open_card;
    }

    public int getOpen_card() {
        return this.open_card;
    }

    public void setOpen_shake(int open_shake) {
        this.open_shake = open_shake;
    }

    public int getOpen_shake() {
        return this.open_shake;
    }

}


class Funcscope_category {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}

class Func_info {
    private Funcscope_category funcscope_category;

    public void setFuncscope_category(Funcscope_category funcscope_category) {
        this.funcscope_category = funcscope_category;
    }

    public Funcscope_category getFuncscope_category() {
        return this.funcscope_category;
    }

}

class Authorization_info {
    private String authorization_appid;

    private List<Func_info> func_info;

    public void setAuthorization_appid(String authorization_appid) {
        this.authorization_appid = authorization_appid;
    }

    public String getAuthorization_appid() {
        return this.authorization_appid;
    }

    public void setFunc_info(List<Func_info> func_info) {
        this.func_info = func_info;
    }

    public List<Func_info> getFunc_info() {
        return this.func_info;
    }

}

class MiniProgramInfo {
    private Network network;

    private List<Categories> categories ;

    private int visit_status;

    public void setNetwork(Network network){
        this.network = network;
    }
    public Network getNetwork(){
        return this.network;
    }
    public void setCategories(List<Categories> categories){
        this.categories = categories;
    }
    public List<Categories> getCategories(){
        return this.categories;
    }
    public void setVisit_status(int visit_status){
        this.visit_status = visit_status;
    }
    public int getVisit_status(){
        return this.visit_status;
    }
}

class Categories {
    private String first;

    private String second;

    public void setFirst(String first){
        this.first = first;
    }
    public String getFirst(){
        return this.first;
    }
    public void setSecond(String second){
        this.second = second;
    }
    public String getSecond(){
        return this.second;
    }

}

class Network {
    private List<String> RequestDomain ;

    private List<String> WsRequestDomain ;

    private List<String> UploadDomain ;

    private List<String> DownloadDomain ;

    public List<String> getRequestDomain() {
        return RequestDomain;
    }

    public void setRequestDomain(List<String> requestDomain) {
        RequestDomain = requestDomain;
    }

    public List<String> getWsRequestDomain() {
        return WsRequestDomain;
    }

    public void setWsRequestDomain(List<String> wsRequestDomain) {
        WsRequestDomain = wsRequestDomain;
    }

    public List<String> getUploadDomain() {
        return UploadDomain;
    }

    public void setUploadDomain(List<String> uploadDomain) {
        UploadDomain = uploadDomain;
    }

    public List<String> getDownloadDomain() {
        return DownloadDomain;
    }

    public void setDownloadDomain(List<String> downloadDomain) {
        DownloadDomain = downloadDomain;
    }
}
