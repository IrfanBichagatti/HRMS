package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.AssetStock;
import com.hrms.Hrmsbackend.models.AssetView;
import com.hrms.Hrmsbackend.models.EmpoyeeAssets;
import com.hrms.Hrmsbackend.service.AssetManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class AssetManagerController {

    @Autowired
    private AssetManagerService assetManagerService;

    /*
     * Asset Manager
     * Add new asset in a stock
     * */
    @PostMapping("/addAssetStock")
    private AssetStock addAssetStock(@RequestBody AssetStock assetStock){
        return assetManagerService.addAssetStock(assetStock);
    }
    /*
     * Asset Manager
     * get table for Asset view module
     * */

    @PostMapping("/addAssetView")//add asset to asset view
    private AssetView UpdateAssetView(@RequestBody AssetView assetView){
        return assetManagerService.UpdateAssetView(assetView);
    }
    /*
     * Asset Manager
     * update asset view table
     * */
    @PutMapping("/UpdateAssetToAssetView")//add asset to asset view
    private AssetView UpdateAssetToAssetView(@RequestBody AssetView assetView){
        return assetManagerService.UpdateAssetToAssetView(assetView);
    }
    /*
     * Asset Manager
     * Asset view Filter  by Name
     * */
    @GetMapping("/getAssetByName/{assetName}")
    private AssetView getAssetByName(@PathVariable("assetName") String assetName){
        return assetManagerService.getAssetByName(assetName);
    }
    /*
     * Asset Manager
     * get Asset View
     * */
    @GetMapping("/getAssetView")
    private List<AssetView> getAssetView(){
        return assetManagerService.getAssetView();
    }
    /*
     * Asset Manager
     * Assign Asset to Employee
     * */
    @PostMapping("/assignAssets")
    private Map<String,String> assignAssets(@RequestBody EmpoyeeAssets empoyeeAssets){
        return assetManagerService.assignAssets(empoyeeAssets);
    }
    /*
     * Asset Manager
     * get list of Employee Asset
     * */
    @GetMapping("/getEmployeeAsset")
    private List<EmpoyeeAssets> getEmployeeAsset(){
        return assetManagerService.getEmployeeAsset();
    }

}
