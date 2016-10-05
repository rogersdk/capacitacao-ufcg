package br.edu.ufcg.embedded.aula10;

/**
 * Created by rogerio on 04/10/16.
 */
public class ApiManager {

    private static final String host = "http://ec2-52-67-176-213.sa-east-1.compute.amazonaws.com:9000/api/v1/";

    private static final String getUserResource = "users/";
    private static final String getContactsResource = "users/:userId/contacts";

    static ApiManager instance;

    private ApiManager() {

    }

    public static ApiManager getInstance() {
        if(instance == null) {
            instance = new ApiManager();
        }

        return instance;
    }

    public String getUser(String id) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(host);
        buffer.append(getUserResource);
        buffer.append(id);


        return buffer.toString();
    }

    public String getGetContacts(String userId) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(host);
        buffer.append(getContactsResource.replace(":userId", userId));

        return buffer.toString();
    }


}
