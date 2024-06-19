//package org.abigfish.milvus;
//
//import com.alibaba.fastjson.JSONObject;
//import io.milvus.v2.client.ConnectConfig;
//import io.milvus.v2.client.MilvusClientV2;
//import io.milvus.v2.service.collection.request.CreateCollectionReq;
//import io.milvus.v2.service.collection.request.GetLoadStateReq;
//import io.milvus.v2.service.partition.request.CreatePartitionReq;
//import io.milvus.v2.service.vector.request.InsertReq;
//import io.milvus.v2.service.vector.request.SearchReq;
//import io.milvus.v2.service.vector.response.InsertResp;
//import io.milvus.v2.service.vector.response.SearchResp;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
///**
// * @author machenggong
// * @since 2024/6/3
// */
//public class Search {
//	
//	private static String collectionName = "vector_store_2";
//
//    public static void main(String[] args) {
//        // 1. Connect to Milvus server
//        ConnectConfig connectConfig = ConnectConfig.builder().uri("http://192.168.2.220:19530").build();
//
//        MilvusClientV2 client = new MilvusClientV2(connectConfig);
//        initData(client);
//        search1(client);
//        searchPartition(client);
//        searchField(client);
//    }
//
//    public static void searchField(MilvusClientV2 client){
//        List<List<Float>> query_vectors = Arrays.asList(Arrays.asList(0.3580376395471989f, -0.6023495712049978f, 0.18414012509913835f, -0.26286205330961354f, 0.9029438446296592f));
//
//        SearchReq searchReq = SearchReq.builder()
//                .collectionName(collectionName)
//                .data(query_vectors)
//                .outputFields(Arrays.asList("color"))
//                .topK(5)
//                .build();
//
//        SearchResp searchResp = client.search(searchReq);
//
//        System.out.println(JSONObject.toJSON(searchResp));
//    }
//
//    public static void searchPartition(MilvusClientV2 client){
//        List<List<Float>> query_vectors = Arrays.asList(Arrays.asList(0.3580376395471989f, -0.6023495712049978f, 0.18414012509913835f, -0.26286205330961354f, 0.9029438446296592f));
//
//        SearchReq searchReq = SearchReq.builder()
//                .collectionName(collectionName)
//                .data(query_vectors)
//                .topK(5) // The number of results to return
//                .partitionNames(Arrays.asList("red"))
//                .build();
//
//        SearchResp searchResp = client.search(searchReq);
//
//        System.out.println(JSONObject.toJSON(searchResp));
//    }
//
//    public static void search1(MilvusClientV2 client){
//// 4. Single vector search
//        List<List<Float>> query_vectors = Arrays.asList(Arrays.asList(0.3580376395471989f, -0.6023495712049978f, 0.18414012509913835f, -0.26286205330961354f, 0.9029438446296592f));
//
//        SearchReq searchReq = SearchReq.builder()
//                .collectionName(collectionName)
//                .data(query_vectors)
//                .topK(3) // The number of results to return
//                .build();
//
//        SearchResp searchResp = client.search(searchReq);
//
//        System.out.println(JSONObject.toJSON(searchResp));
//    }
//
//    public static void initData(MilvusClientV2 client) {
//        // 2. Create a collection in quick setup mode
////        CreateCollectionReq quickSetupReq = CreateCollectionReq.builder().collectionName("quick_setup").dimension(5).metricType("IP").build();
////
////        client.createCollection(quickSetupReq);
////
////        GetLoadStateReq loadStateReq = GetLoadStateReq.builder().collectionName("quick_setup").build();
////
////        boolean state = client.getLoadState(loadStateReq);
////
////        System.out.println(state);
//
//// Output:
//// true
//
//// 3. Insert randomly generated vectors into the collection
//        List<String> colors = Arrays.asList("green", "blue", "yellow", "red", "black", "white", "purple", "pink", "orange", "brown", "grey");
//        List<JSONObject> data = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i++) {
//            Random rand = new Random();
//            String current_color = colors.get(rand.nextInt(colors.size() - 1));
//            JSONObject row = new JSONObject();
//            row.put("id", Long.valueOf(i));
//            row.put("vector", Arrays.asList(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
//            row.put("color_tag", current_color + "_" + String.valueOf(rand.nextInt(8999) + 1000));
//            data.add(row);
//        }
//
//        InsertReq insertReq = InsertReq.builder().collectionName(collectionName).data(data).build();
//
//        InsertResp insertResp = client.insert(insertReq);
//
//        System.out.println(JSONObject.toJSON(insertResp));
//
//// Output:
//// {"insertCnt": 1000}
//
//// 6.1. Create a partition
//        CreatePartitionReq partitionReq = CreatePartitionReq.builder().collectionName(collectionName).partitionName("red").build();
//
//        client.createPartition(partitionReq);
//
//        partitionReq = CreatePartitionReq.builder().collectionName(collectionName).partitionName("blue").build();
//
//        client.createPartition(partitionReq);
//
//// 6.2 Insert data into the partition
//        data = new ArrayList<>();
//
//        for (int i = 1000; i < 1500; i++) {
//            Random rand = new Random();
//            String current_color = "red";
//            JSONObject row = new JSONObject();
//            row.put("id", Long.valueOf(i));
//            row.put("vector", Arrays.asList(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
//            row.put("color", current_color);
//            row.put("color_tag", current_color + "_" + String.valueOf(rand.nextInt(8999) + 1000));
//            data.add(row);
//        }
//
//        insertReq = InsertReq.builder().collectionName(collectionName).data(data).partitionName("red").build();
//
//        insertResp = client.insert(insertReq);
//
//        System.out.println(JSONObject.toJSON(insertResp));
//
//// Output:
//// {"insertCnt": 500}
//
//        data = new ArrayList<>();
//
//        for (int i = 1500; i < 2000; i++) {
//            Random rand = new Random();
//            String current_color = "blue";
//            JSONObject row = new JSONObject();
//            row.put("id", Long.valueOf(i));
//            row.put("vector", Arrays.asList(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
//            row.put("color", current_color);
//            row.put("color_tag", current_color + "_" + String.valueOf(rand.nextInt(8999) + 1000));
//            data.add(row);
//        }
//
//        insertReq = InsertReq.builder().collectionName(collectionName).data(data).partitionName("blue").build();
//
//        insertResp = client.insert(insertReq);
//
//        System.out.println(JSONObject.toJSON(insertResp));
//
//// Output:
//// {"insertCnt": 500}
//
//
//    }
//
//}
