/*************************************************//**
 *  \brief     Kurzbeschreibung: Class ZusePongApp
 *  \details   Application Start + main Methode
 *  \author    Marcel Hesselbach
 *  \version   1.0
 ***********************************************/

package de.htwsaar.pong.zuse;

import de.htwsaar.pong.zuse.model.LaunchGame;

/**
 * Klasse ZusePongApp
 * repräsentiert den Application Launcher und startet lediglich die Methode launchGame in der Klasse
 * LaunchGame mit args
 */
public class ZusePongApp {
  public static void main(String[] args) {
    LaunchGame.launchGame(args);
  }
}
