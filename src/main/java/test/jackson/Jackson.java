package test.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by mmamula on 10.12.2016.
 */
public class Jackson {

    public static void stream() {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(outputStream);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("test");
            jsonGenerator.writeNumber(1);
            jsonGenerator.writeFieldName("secondš");
            jsonGenerator.writeString("test");
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            System.out.println(outputStream.toString("UTF-8"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void tree() {
        try {
            ObjectMapper om = new ObjectMapper();
            ObjectNode on = om.createObjectNode();
            on.put("test", 1);
            ArrayNode ar = on.putArray("array");
            ar.add("first");
            ar.add("second");
            ar.add("šđč");
            System.out.println(on.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void pojo() {
        try {
            ObjectMapper om = new ObjectMapper();
            om.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            Pojo p = new Pojo();
            om.writeValue(System.out, p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void strTest() {
        String json = "{\"ticket\":{\"url\":\"https://infobiphelp1451563223.zendesk.com/api/v2/tickets/2263.json\",\"id\":2263,\"external_id\":123,\"via\":{\"channel\":\"api\",\"source\":{\"from\":{},\"to\":{},\"rel\":null}},\"created_at\":\"2017-01-11T11:29:27Z\",\"updated_at\":\"2017-01-11T11:29:27Z\",\"type\":null,\"subject\":\"Test ticket\",\"raw_subject\":\"Test ticket\",\"description\":\"Test ticket comment\",\"priority\":\"urgent\",\"status\":\"open\",\"recipient\":null,\"requester_id\":3117676185,\"submitter_id\":3117676185,\"assignee_id\":3100226209,\"organization_id\":2067345415,\"group_id\":25648985,\"collaborator_ids\":[],\"forum_topic_id\":null,\"problem_id\":null,\"has_incidents\":false,\"is_public\":true,\"due_at\":null,\"tags\":[\"isactive\",\"iskeyclient\"],\"custom_fields\":[{\"id\":30633965,\"value\":\"\"},{\"id\":30498569,\"value\":\"\"},{\"id\":27070529,\"value\":\"\"},{\"id\":27178325,\"value\":\"\"},{\"id\":28313705,\"value\":\"\"},{\"id\":27070829,\"value\":\"\"},{\"id\":27070329,\"value\":null},{\"id\":25897929,\"value\":null},{\"id\":28836865,\"value\":null},{\"id\":28836885,\"value\":null},{\"id\":26196705,\"value\":\"\"},{\"id\":25895919,\"value\":\"\"},{\"id\":26000895,\"value\":\"\"},{\"id\":28182849,\"value\":false},{\"id\":28824185,\"value\":\"\"},{\"id\":29920325,\"value\":null},{\"id\":30497609,\"value\":null},{\"id\":30634265,\"value\":\"\"},{\"id\":33101425,\"value\":\"\"},{\"id\":32938389,\"value\":\"\"},{\"id\":33928165,\"value\":null}],\"satisfaction_rating\":{\"score\":\"unoffered\"},\"sharing_agreement_ids\":[],\"fields\":[{\"id\":30633965,\"value\":\"\"},{\"id\":30498569,\"value\":\"\"},{\"id\":27070529,\"value\":\"\"},{\"id\":27178325,\"value\":\"\"},{\"id\":28313705,\"value\":\"\"},{\"id\":27070829,\"value\":\"\"},{\"id\":27070329,\"value\":null},{\"id\":25897929,\"value\":null},{\"id\":28836865,\"value\":null},{\"id\":28836885,\"value\":null},{\"id\":26196705,\"value\":\"\"},{\"id\":25895919,\"value\":\"\"},{\"id\":26000895,\"value\":\"\"},{\"id\":28182849,\"value\":false},{\"id\":28824185,\"value\":\"\"},{\"id\":29920325,\"value\":null},{\"id\":30497609,\"value\":null},{\"id\":30634265,\"value\":\"\"},{\"id\":33101425,\"value\":\"\"},{\"id\":32938389,\"value\":\"\"},{\"id\":33928165,\"value\":null}],\"ticket_form_id\":null,\"brand_id\":410699,\"satisfaction_probability\":null,\"allow_channelback\":false},\"audit\":{\"id\":120052727485,\"ticket_id\":2263,\"created_at\":\"2017-01-11T11:29:27Z\",\"author_id\":3117676185,\"via\":{\"channel\":\"api\",\"source\":{\"from\":{},\"to\":{},\"rel\":null}},\"metadata\":{\"system\":{\"ip_address\":\"89.164.98.253\",\"location\":\"Croatia\",\"latitude\":45.16669999999999,\"longitude\":15.5},\"custom\":{}},\"events\":[{\"id\":120052727525,\"type\":\"Comment\",\"author_id\":3117676185,\"body\":\"Test ticket comment\",\"html_body\":\"<div class=\\\"zd-comment\\\"><p dir=\\\"auto\\\">Test ticket comment</p></div>\",\"plain_body\":\"Test ticket comment\",\"public\":true,\"attachments\":[],\"audit_id\":120052727485},{\"id\":120052727785,\"type\":\"Create\",\"value\":\"Test ticket\",\"field_name\":\"subject\"},{\"id\":120052727825,\"type\":\"Create\",\"value\":\"3117676185\",\"field_name\":\"requester_id\"},{\"id\":120052727845,\"type\":\"Create\",\"value\":\"new\",\"field_name\":\"status\"},{\"id\":120052727885,\"type\":\"Create\",\"value\":null,\"field_name\":\"priority\"},{\"id\":120052727925,\"type\":\"Create\",\"value\":null,\"field_name\":\"type\"},{\"id\":120052727985,\"type\":\"Create\",\"value\":\"2067345415\",\"field_name\":\"organization_id\"},{\"id\":120052728025,\"type\":\"Create\",\"value\":[\"isactive\",\"iskeyclient\"],\"field_name\":\"tags\"},{\"id\":120052728085,\"type\":\"Create\",\"value\":\"410699\",\"field_name\":\"brand_id\"},{\"id\":120052728145,\"type\":\"Notification\",\"via\":{\"channel\":\"rule\",\"source\":{\"to\":{},\"from\":{\"id\":47380489,\"title\":\"Notify requester of received request [Infobip]\"},\"rel\":\"trigger\"}},\"subject\":\"SANDBOX[IB#{{ticket.id}}] {{ticket.title}}\",\"body\":\"{% if ticket.cc_names != empty %}\\nCC parties registered for this ticket:\\n\\n{% capture ccedusers %}\\n\\n{% for cc in ticket.ccs %}\\n{% unless forloop.last %}\\n{{ cc.email | append: ', ' }}\\n{% else %}\\n{{ cc.email }}\\n{% endunless %}\\n{% endfor %}\\n\\n{% endcapture %}\\n\\n{{ ccedusers | strip_newlines }}\\n\\n{% endif %} \\n{{ticket.comments_formatted}}\\n\\n<a style=\\\"text-decoration: none\\\" title=\\\"visit infobip website\\\" href=\\\"http://www.infobip.com\\\">\\n                    <img border=\\\"0\\\" hspace=\\\"0\\\" alt=\\\"visit infobip website\\\" src=\\\"http://www.infobip.com/images/signatures/infobip_signature.gif\\\">\\n                    </a>\\n<p><a style=\\\"text-decoration: none\\\" href=\\\"http://www.infobip.com\\\"><font style=\\\"font-size: 8pt\\\" color=\\\"#ff7000\\\" face=\\\"arial\\\"><strong>www.infobip.com</strong></font></a><font style=\\\"font-size: 8pt\\\" color=\\\"gray\\\" face=\\\"arial\\\">&nbsp;/&nbsp;<strong>GSMA Associate Member</strong>&nbsp;/&nbsp;<strong>Mobey Forum member</strong></font><br><font style=\\\"font-size: 7pt\\\" color=\\\"#a5a5a5\\\" face=\\\"arial\\\">This message is private and confidential. Any views or opinions expressed are solely those of the author and do not necessarily represent those of Infobip. If you have received this message in error, please notify us immediately via email to <a href=\\\"mailto:support@infobip.com\\\" style=\\\"color:#a5a5a5;\\\"><font style=\\\"font-size: 7pt\\\" color=\\\"#a5a5a5\\\" face=\\\"arial\\\">support@infobip.com</font></a> or telephone +442032864231.</font></p>\",\"recipients\":[3117676185]},{\"id\":120052728205,\"type\":\"Change\",\"via\":{\"channel\":\"rule\",\"source\":{\"to\":{},\"from\":{\"id\":76787485,\"title\":\"Ticket order\"},\"rel\":\"trigger\"}},\"value\":\"25648985\",\"field_name\":\"group_id\",\"previous_value\":null},{\"id\":120052728285,\"type\":\"Change\",\"via\":{\"channel\":\"rule\",\"source\":{\"to\":{},\"from\":{\"id\":76787485,\"title\":\"Ticket order\"},\"rel\":\"trigger\"}},\"value\":\"urgent\",\"field_name\":\"priority\",\"previous_value\":null},{\"id\":120052728325,\"type\":\"Change\",\"via\":{\"channel\":\"rule\",\"source\":{\"to\":{},\"from\":{\"id\":76787485,\"title\":\"Ticket order\"},\"rel\":\"trigger\"}},\"value\":\"3100226209\",\"field_name\":\"assignee_id\",\"previous_value\":null},{\"id\":120052728365,\"type\":\"Change\",\"via\":{\"channel\":\"rule\",\"source\":{\"to\":{},\"from\":{\"id\":76787485,\"title\":\"Ticket order\"},\"rel\":\"trigger\"}},\"value\":\"open\",\"field_name\":\"status\",\"previous_value\":\"new\"}]}}";
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        try {
            JsonNode node = om.readTree(json);
            System.out.println(node.has("ticket"));
            //System.out.println(node.get("ticket").toString());
            ZendeskTicket newZendeskTicket = om.readValue(node.get("ticket").toString(), ZendeskTicket.class);
            System.out.println(newZendeskTicket);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void strToPojo() {
        try {
            ObjectMapper om = new ObjectMapper();
            ZendeskTicket p = om.readValue("{\"subject\": \"Test ticket 6\", \"comment\": { \"body\": \"Test ticket comment 6\" }, \"recipient\": \"Mihael.Mamula@infobip.com\", \"requester\": { \"name\": \"Mihael\", \"email\": \"Mihael.Mamula@infobip.com\" }, \"collaborators\": [\"test@mail.com\"]}", ZendeskTicket.class);
            System.out.println(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}

@Data
class Pojo {
    public String nameTest = "Test";
    public int number = 12;
    public Pojo () {}
}

@Data
class ZendeskTicket {

    private Integer id;
    private Integer externalId;
    private String subject;
    private String recipient;
    private String description;
    private String status;
    private String type;
    private String priority;
    private Comment comment;
    private Requester requester;
    private ArrayList<String> collaborators;

    @Data
    class Comment {
        String body;
    }

    @Data
    class Requester {
        String name;
        String email;
    }

}
