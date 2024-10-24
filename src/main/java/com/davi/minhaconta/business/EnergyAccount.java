package com.davi.minhaconta.business;

import com.ions.lightdealer.sdk.model.Address;
import com.ions.lightdealer.sdk.model.Client;
import com.ions.lightdealer.sdk.model.ElectronicDevice;


/**
 * The type Energy account.
 */
public class EnergyAccount {

  Client client;

  public EnergyAccount(Client client) {
    this.client = client;
  }

  /**
   * Req. 11 – Find high consumption device per address.
   */
  public ElectronicDevice[] findHighConsumptionDevices() {
    Address[] addresses = client.getAddressesAsArray();
    ElectronicDevice[] result = new ElectronicDevice[addresses.length];
    for (int i = 0; i < result.length; i++) {
      ElectronicDevice[] electro = addresses[i].getDevicesAsArray();
      if (electro.length == 0) {
        result[i] = null;
      } else {
        ElectronicDevice electroMax = electro[0];
        for (ElectronicDevice device : electro) {
          if (electroMax.monthlyKwh() < device.monthlyKwh()) {
            electroMax = device;
          }
        }
        result[i] = electroMax;
      }
    }
    return result;
  }
}
