package group13.ecobikerental.subsystem.interbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.utils.Utils;

public class Request {
    private String version = Configs.VERSION;
    private Transaction transaction;
    private String appCode = Configs.APPCODE;
    private String hashCode;

    public Request(Transaction transaction) {
        this.transaction = transaction;
        this.hashCode = makeHashCode();
        System.out.println(this.hashCode);
    }

    private String makeHashCode() {
        Gson gson = new Gson();
        String transactionJO = gson.toJson(this.transaction, Transaction.class);
        System.out.println(transactionJO);
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("secretKey", Configs.SECRETKEY);
        jsonObject.addProperty("transaction", transactionJO);
        return Utils.md5(jsonObject.toString());
    }

    public String makeRequestJson() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this, Request.class);
    }

//    test
    public static void main(String[] args) {
        Transaction transaction = new Transaction("vn_group2_2021", "Group 2", "774", "1125", "pay", "test pay", 100);
        Request request = new Request(transaction);
        String test = request.makeHashCode();
        System.out.println(Utils.md5(test));
        System.out.println(request.makeRequestJson());
    }
}
