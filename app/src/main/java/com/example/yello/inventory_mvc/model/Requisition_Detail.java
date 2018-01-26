package com.example.yello.inventory_mvc.model;

import com.example.yello.inventory_mvc.utility.JSONParser;
import com.example.yello.inventory_mvc.utility.Key;
import com.example.yello.inventory_mvc.utility.UrlString;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Created by CK Tan on 1/23/2018.
 */

public class Requisition_Detail extends HashMap<String, String>
{
    public Requisition_Detail(String requisitionNo, String itemCode, String description,
                              String uom, String remarks, String requestQty,
                              String fulfilledQty, String clerkID, String retrievedDate,
                              String allocateQty, String nextCollectionDate)
    {
        this.put(Key.REQUISITION_DETAIL_1_REQUISITION_NO, requisitionNo);
        this.put(Key.REQUISITION_DETAIL_2_ITEM_CODE, itemCode);
        this.put(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION, description);
        this.put(Key.REQUISITION_DETAIL_4_ITEM_UOM, uom);
        this.put(Key.REQUISITION_DETAIL_5_REMARKS, remarks);
        this.put(Key.REQUISITION_DETAIL_6_REQUEST_QTY, requestQty);
        this.put(Key.REQUISITION_DETAIL_7_FULFILLED_QTY, fulfilledQty);
        this.put(Key.REQUISITION_DETAIL_8_CLERK_ID, clerkID);
        this.put(Key.REQUISITION_DETAIL_9_RETRIEVED_DATE, retrievedDate);
        this.put(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY, allocateQty);
        this.put(Key.REQUISITION_DETAIL_11_NEXT_COLLECTION_DATE, nextCollectionDate);
    }

    public static List<Requisition_Detail> getDetailsByReqNo(String deptCode)
    {
        String url = UrlString.getDetailsByReqNo + deptCode;
        ArrayList<Requisition_Detail> reqDetails = new ArrayList<>();

        try
        {
            JSONArray array = JSONParser.getJSONArrayFromUrl(url);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                reqDetails.add(new Requisition_Detail(obj.getString(Key.REQUISITION_DETAIL_1_REQUISITION_NO),
                        obj.getString(Key.REQUISITION_DETAIL_2_ITEM_CODE),
                        obj.getString(Key.REQUISITION_DETAIL_3_ITEM_DESCRIPTION),
                        obj.getString(Key.REQUISITION_DETAIL_4_ITEM_UOM),
                        obj.getString(Key.REQUISITION_DETAIL_5_REMARKS),
                        obj.getString(Key.REQUISITION_DETAIL_6_REQUEST_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_7_FULFILLED_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_8_CLERK_ID),
                        obj.getString(Key.REQUISITION_DETAIL_9_RETRIEVED_DATE),
                        obj.getString(Key.REQUISITION_DETAIL_10_ALLOCATE_QTY),
                        obj.getString(Key.REQUISITION_DETAIL_11_NEXT_COLLECTION_DATE)
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return reqDetails;
    }

    
}
