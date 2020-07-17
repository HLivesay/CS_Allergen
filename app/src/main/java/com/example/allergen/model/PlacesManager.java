package com.example.allergen.model;

import java.util.ArrayList;
import java.util.List;

import com.example.allergen.data.PlacesResponse;
import com.example.allergen.data.PlacesResponse_Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PlacesManager {

    public static List<PlacesResponse_Restaurant> getRestaurantIDinRange(int range) throws Exception {
        if(range< 0 || range > 150)
            throw new Exception ("Range must be nonnegative and less than 150");
        String response = "{\n" +
                "   \"html_attributions\" : [],\n" +
                "   \"results\" : [\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : -33.870775,\n" +
                "               \"lng\" : 151.199025\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"http://maps.gstatic.com/mapfiles/place_api/icons/travel_agent-71.png\",\n" +
                "         \"id\" : \"21a0b251c9b8392186142c798263e289fe45b4aa\",\n" +
                "         \"name\" : \"Rhythmboat Cruises\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 270,\n" +
                "               \"html_attributions\" : [],\n" +
                "               \"photo_reference\" : \"CnRnAAAAF-LjFR1ZV93eawe1cU_3QNMCNmaGkowY7CnOf-kcNmPhNnPEG9W979jOuJJ1sGr75rhD5hqKzjD8vbMbSsRnq_Ni3ZIGfY6hKWmsOf3qHKJInkm4h55lzvLAXJVc-Rr4kI9O1tmIblblUpg2oqoq8RIQRMQJhFsTr5s9haxQ07EQHxoUO0ICubVFGYfJiMUPor1GnIWb5i8\",\n" +
                "               \"width\" : 519\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJyWEHuEmuEmsRm9hTkapTCrk\",\n" +
                "         \"reference\" : \"CoQBdQAAAFSiijw5-cAV68xdf2O18pKIZ0seJh03u9h9wk_lEdG-cP1dWvp_QGS4SNCBMk_fB06YRsfMrNkINtPez22p5lRIlj5ty_HmcNwcl6GZXbD2RdXsVfLYlQwnZQcnu7ihkjZp_2gk1-fWXql3GQ8-1BEGwgCxG-eaSnIJIBPuIpihEhAY1WYdxPvOWsPnb2-nGb6QGhTipN0lgaLpQTnkcMeAIEvCsSa0Ww\",\n" +
                "         \"types\" : [ \"travel_agency\", \"restaurant\", \"food\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"Pyrmont Bay Wharf Darling Dr, Sydney\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : -33.866891,\n" +
                "               \"lng\" : 151.200814\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"http://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png\",\n" +
                "         \"id\" : \"45a27fd8d56c56dc62afc9b49e1d850440d5c403\",\n" +
                "         \"name\" : \"Private Charter Sydney Habour Cruise\",\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 426,\n" +
                "               \"html_attributions\" : [],\n" +
                "               \"photo_reference\" : \"CnRnAAAAL3n0Zu3U6fseyPl8URGKD49aGB2Wka7CKDZfamoGX2ZTLMBYgTUshjr-MXc0_O2BbvlUAZWtQTBHUVZ-5Sxb1-P-VX2Fx0sZF87q-9vUt19VDwQQmAX_mjQe7UWmU5lJGCOXSgxp2fu1b5VR_PF31RIQTKZLfqm8TA1eynnN4M1XShoU8adzJCcOWK0er14h8SqOIDZctvU\",\n" +
                "               \"width\" : 640\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJqwS6fjiuEmsRJAMiOY9MSms\",\n" +
                "         \"reference\" : \"CpQBhgAAAFN27qR_t5oSDKPUzjQIeQa3lrRpFTm5alW3ZYbMFm8k10ETbISfK9S1nwcJVfrP-bjra7NSPuhaRulxoonSPQklDyB-xGvcJncq6qDXIUQ3hlI-bx4AxYckAOX74LkupHq7bcaREgrSBE-U6GbA1C3U7I-HnweO4IPtztSEcgW09y03v1hgHzL8xSDElmkQtRIQzLbyBfj3e0FhJzABXjM2QBoUE2EnL-DzWrzpgmMEulUBLGrtu2Y\",\n" +
                "         \"types\" : [ \"restaurant\", \"food\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"Australia\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : -33.870943,\n" +
                "               \"lng\" : 151.190311\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"http://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png\",\n" +
                "         \"id\" : \"30bee58f819b6c47bd24151802f25ecf11df8943\",\n" +
                "         \"name\" : \"Bucks Party Cruise\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 600,\n" +
                "               \"html_attributions\" : [],\n" +
                "               \"photo_reference\" : \"CnRnAAAA48AX5MsHIMiuipON_Lgh97hPiYDFkxx_vnaZQMOcvcQwYN92o33t5RwjRpOue5R47AjfMltntoz71hto40zqo7vFyxhDuuqhAChKGRQ5mdO5jv5CKWlzi182PICiOb37PiBtiFt7lSLe1SedoyrD-xIQD8xqSOaejWejYHCN4Ye2XBoUT3q2IXJQpMkmffJiBNftv8QSwF4\",\n" +
                "               \"width\" : 800\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJLfySpTOuEmsRsc_JfJtljdc\",\n" +
                "         \"reference\" : \"CoQBdQAAANQSThnTekt-UokiTiX3oUFT6YDfdQJIG0ljlQnkLfWefcKmjxax0xmUpWjmpWdOsScl9zSyBNImmrTO9AE9DnWTdQ2hY7n-OOU4UgCfX7U0TE1Vf7jyODRISbK-u86TBJij0b2i7oUWq2bGr0cQSj8CV97U5q8SJR3AFDYi3ogqEhCMXjNLR1k8fiXTkG2BxGJmGhTqwE8C4grdjvJ0w5UsAVoOH7v8HQ\",\n" +
                "         \"types\" : [ \"restaurant\", \"food\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"37 Bank St, Pyrmont\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : -33.867591,\n" +
                "               \"lng\" : 151.201196\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"http://maps.gstatic.com/mapfiles/place_api/icons/travel_agent-71.png\",\n" +
                "         \"id\" : \"a97f9fb468bcd26b68a23072a55af82d4b325e0d\",\n" +
                "         \"name\" : \"Australian Cruise Group\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 242,\n" +
                "               \"html_attributions\" : [],\n" +
                "               \"photo_reference\" : \"CnRnAAAABjeoPQ7NUU3pDitV4Vs0BgP1FLhf_iCgStUZUr4ZuNqQnc5k43jbvjKC2hTGM8SrmdJYyOyxRO3D2yutoJwVC4Vp_dzckkjG35L6LfMm5sjrOr6uyOtr2PNCp1xQylx6vhdcpW8yZjBZCvVsjNajLBIQ-z4ttAMIc8EjEZV7LsoFgRoU6OrqxvKCnkJGb9F16W57iIV4LuM\",\n" +
                "               \"width\" : 200\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJrTLr-GyuEmsRBfy61i59si0\",\n" +
                "         \"reference\" : \"CoQBeQAAAFvf12y8veSQMdIMmAXQmus1zqkgKQ-O2KEX0Kr47rIRTy6HNsyosVl0CjvEBulIu_cujrSOgICdcxNioFDHtAxXBhqeR-8xXtm52Bp0lVwnO3LzLFY3jeo8WrsyIwNE1kQlGuWA4xklpOknHJuRXSQJVheRlYijOHSgsBQ35mOcEhC5IpbpqCMe82yR136087wZGhSziPEbooYkHLn9e5njOTuBprcfVw\",\n" +
                "         \"types\" : [ \"travel_agency\", \"restaurant\", \"food\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"32 The Promenade, King Street Wharf 5, Sydney\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        PlacesResponse pr = gson.fromJson(response, PlacesResponse.class);
        if(pr.status == "OK")
            return pr.results;

        return pr.results;
    }
}
