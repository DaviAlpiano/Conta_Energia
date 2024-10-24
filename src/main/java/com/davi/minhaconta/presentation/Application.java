package com.davi.minhaconta.presentation;

import com.davi.minhaconta.business.EnergyAccount;
import com.davi.minhaconta.business.EnergyBill;
import com.ions.lightdealer.sdk.model.Address;
import com.ions.lightdealer.sdk.model.Client;
import com.ions.lightdealer.sdk.model.ElectronicDevice;
import com.ions.lightdealer.sdk.service.LightDealerApi;

/**
 * The type Application.
 */
public class Application {

  ConsoleUserInterface ui;
  LightDealerApi api;
  String[] options = {
      "1 - Cadastrar cliente",
      "2 - Cadastrar imóvel de cliente",
      "3 - Cadastrar dispositivos em imóvel",
      "4 - Estimar conta de imóvel",
      "5 - Otimizar uso de energia",
      "6 - Sair"
  };

  /**
   * Constructor that instantiates a new Application.
   */
  public Application(ConsoleUserInterface ui) {
    this.ui = ui;
    this.api = new LightDealerApi();
  }

  /**
   * Req. 4 – Creates CLI menu.
   */
  public void run() {
    char option = ui.inputMenuOption(options);

    while (option != '6') {
      runOptionAction(option);
      option = ui.inputMenuOption(options);
    }
    runOptionAction(option);
  }

  /**
   * Req. 5 – Run menu options.
   */
  public void runOptionAction(char option) {
    switch (option) {
      case '1': this.registerClient();
        break;
      case '2': this.registerClientAddress();
        break;
      case '3': this.registerAddressDevices();
        break;
      case '4': this.estimateAddressBill();
        break;
      case '5': this.optimizeEnergyBill();
        break;
      case '6': ui.showMessage("Volte sempre!");
        break;
      default: ui.showMessage("Opção inválida!");
    }
  }

  /**
   * Req. 6 – Register client.
   */
  public void registerClient() {
    Client client = new Client();
    ui.fillClientData(client);
    api.addClient(client);
  }

  /**
   * Req. 7 – Register client address.
   */
  public void registerClientAddress() {
    String cpf = ui.inputClientCpf();
    Client client = api.findClient(cpf);
    if (client == null) {
      ui.showMessage("Pessoa cliente não encontrada!");
    } else {
      Address address = new Address();
      ui.fillAddressData(address);
      api.addAddressToClient(address, client);
    }
  }

  /**
   * Req. 8 – Register address devices.
   */
  public void registerAddressDevices() {
    String matricula = ui.inputAddressRegistration();
    Address address = api.findAddress(matricula);
    if (address == null) {
      ui.showMessage("Endereço não encontrado!");
    } else {
      int disp = ui.inputNumberOfDevices();
      for (int i = 0; i < disp; i++) {
        ElectronicDevice electro = new ElectronicDevice();
        ui.fillDeviceData(electro);
        api.addDeviceToAddress(electro, address);
      }
    }
  }

  /**
   * Req. 9 – Estimates the address energy bill.
   */
  public void estimateAddressBill() {
    String matricula = ui.inputAddressRegistration();
    Address address = api.findAddress(matricula);
    if (address == null) {
      ui.showMessage("Endereço não encontrado!");
    } else {
      EnergyBill bill = new EnergyBill(address, true);
      String message = "Valor estimado para a conta: " + bill.estimate();
      ui.showMessage(message);
    }
  }

  /**
   * Req. 10 – Optimizes the energy bill.
   */
  public void optimizeEnergyBill() {
    String cpf = ui.inputClientCpf();
    Client client = api.findClient(cpf);
    if (client == null) {
      ui.showMessage("Pessoa cliente não encontrada!");
    } else {
      EnergyAccount account = new EnergyAccount(client);
      suggestReducedUsage(account);
    }
  }

  /**
   * Req 10 - Aux. Method to display high consumptions devices.
   */
  public void suggestReducedUsage(EnergyAccount energyAccount) {
    ui.showMessage("Considere reduzir o uso dos seguintes dispositivos:");
    ElectronicDevice[] electros = energyAccount.findHighConsumptionDevices();
    for (ElectronicDevice electronicDevice : electros) {
      ui.showMessage(electronicDevice.getName());
    }
  }
}
