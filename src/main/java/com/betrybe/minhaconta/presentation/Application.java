package com.betrybe.minhaconta.presentation;

import com.betrybe.minhaconta.business.EnergyAccount;
import com.ions.lightdealer.sdk.service.LightDealerApi;
import java.util.Scanner;

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
  }

  /**
   * Req. 6 – Register client.
   */
  public void registerClient() {
  }

  /**
   * Req. 7 – Register client address.
   */
  public void registerClientAddress() {
  }

  /**
   * Req. 8 – Register address devices.
   */
  public void registerAddressDevices() {
  }

  /**
   * Req. 9 – Estimates the address energy bill.
   */
  public void estimateAddressBill() {
  }

  /**
   * Req. 10 – Optimizes the energy bill.
   */
  public void optimizeEnergyBill() {
  }

  /**
   * Req 10 - Aux. Method to display high consumptions devices.
   */
  public void suggestReducedUsage(EnergyAccount energyAccount) {
  }
}
