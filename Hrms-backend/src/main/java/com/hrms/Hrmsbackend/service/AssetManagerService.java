package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.AssetStock;
import com.hrms.Hrmsbackend.models.AssetView;
import com.hrms.Hrmsbackend.models.EmpoyeeAssets;
import com.hrms.Hrmsbackend.repo.AssetStockRepo;
import com.hrms.Hrmsbackend.repo.AssetViewRepo;
import com.hrms.Hrmsbackend.repo.EmployeeAssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetManagerService {

    @Autowired
    AssetViewRepo assetViewRepo;
    @Autowired
    EmployeeAssetRepo employeeAssetRepo;
    @Autowired
    private AssetStockRepo assetStockRepo;

    public Map<String,String> assignAssets(EmpoyeeAssets empoyeeAssets) {
        Map<String,String> map = new HashMap<>();
        if("true".equalsIgnoreCase(empoyeeAssets.getLaptop())){
            try {
                AssetView assetView=assetViewRepo.findById("Laptop").orElseThrow();
                int alloted=Integer.parseInt(assetView.getAlloted())+1;
                int inventory=Integer.parseInt(assetView.getTotal())-alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }
        if("true".equalsIgnoreCase(empoyeeAssets.getMonitor())){
            try {
                AssetView assetView=assetViewRepo.findById("Monitor").orElseThrow();
                int alloted=Integer.parseInt(assetView.getAlloted())+1;
                int inventory=Integer.parseInt(assetView.getTotal())-alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }
        if("true".equalsIgnoreCase(empoyeeAssets.getHeadfone())){
            try {
                AssetView assetView = assetViewRepo.findById("Headphone").orElseThrow();
                int alloted = Integer.parseInt(assetView.getAlloted()) + 1;
                int inventory = Integer.parseInt(assetView.getTotal()) - alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }
        if("true".equalsIgnoreCase(empoyeeAssets.getHdmi())){
            try {
                AssetView assetView=assetViewRepo.findById("HDMI Cable").orElseThrow();
                int alloted=Integer.parseInt(assetView.getAlloted())+1;
                int inventory=Integer.parseInt(assetView.getTotal())-alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }
        if("true".equalsIgnoreCase(empoyeeAssets.getEarphone())){
            try {
                AssetView assetView=assetViewRepo.findById("Earphone").orElseThrow();
                int alloted=Integer.parseInt(assetView.getAlloted())+1;
                int inventory=Integer.parseInt(assetView.getTotal())-alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }
        if("true".equalsIgnoreCase(empoyeeAssets.getMobile())){
            try {
                AssetView assetView=assetViewRepo.findById("Mobile").orElseThrow();
                int alloted=Integer.parseInt(assetView.getAlloted())+1;
                int inventory=Integer.parseInt(assetView.getTotal())-alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }
        if("true".equalsIgnoreCase(empoyeeAssets.getPendrive())){
            try {
                AssetView assetView=assetViewRepo.findById("Pendrive").orElseThrow();
                int alloted=Integer.parseInt(assetView.getAlloted())+1;
                int inventory=Integer.parseInt(assetView.getTotal())-alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }
        if("true".equalsIgnoreCase(empoyeeAssets.getLaptopBag())){
            try {
                AssetView assetView=assetViewRepo.findById("Laptop Bag").orElseThrow();
                int alloted=Integer.parseInt(assetView.getAlloted())+1;
                int inventory=Integer.parseInt(assetView.getTotal())-alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }
        if("true".equalsIgnoreCase(empoyeeAssets.getKeyboard())){
            try {
                AssetView assetView=assetViewRepo.findById("Keyboard").orElseThrow();
                int alloted=Integer.parseInt(assetView.getAlloted())+1;
                int inventory=Integer.parseInt(assetView.getTotal())-alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }
        if("true".equalsIgnoreCase(empoyeeAssets.getMouse())){
            try {
                AssetView assetView=assetViewRepo.findById("Mouse").orElseThrow();
                int alloted=Integer.parseInt(assetView.getAlloted())+1;
                int inventory=Integer.parseInt(assetView.getTotal())-alloted;
                if (inventory >= 0) {
                    assetView.setInventory(String.valueOf(inventory));
                    assetView.setAlloted(String.valueOf(alloted));
                    assetViewRepo.save(assetView);

                    employeeAssetRepo.save(empoyeeAssets);
                    map.put("status", "success");
                } else {
                    map.put("status", "error");
                }
            }catch (Exception e){
                map.put("status", "error");
            }
        }

        return   map;
    }

    public List<EmpoyeeAssets> getEmployeeAsset() {
        return employeeAssetRepo.findAll();
    }

    public AssetView UpdateAssetToAssetView(AssetView assetView) {
        return assetViewRepo.save(assetView);
    }

    public AssetView getAssetByName(String assetName) {
        return assetViewRepo.findById(assetName).orElseThrow();
    }

    public AssetStock addAssetStock(AssetStock assetStock) {
        return assetStockRepo.save(assetStock);
    }

    public AssetView UpdateAssetView(AssetView assetView) {
        return assetViewRepo.save(assetView);
    }

    public List<AssetView> getAssetView() {
        return assetViewRepo.findAll();
    }
}
