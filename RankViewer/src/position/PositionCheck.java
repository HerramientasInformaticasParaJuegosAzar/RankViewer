/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package position;

import card.Play;
import java.util.Arrays;
import rankMatrix.RankMatrix;

/**
 *
 * @author usuario
 */
public class PositionCheck {

    /*Supongoamos porcentajes
     BB:100%
     SM:37%
     BTN: 47%
     CO: 24%
     MP: 18%
     UTG: 14%
     */
    public static boolean checkPosition(Play p, PossiblePositions pos, RankMatrix matrix) {
        switch (pos) {
            case BB:
                return true;
            case SB:
                if (Arrays.asList(matrix.perc(37)).contains(p)) {
                    return true;
                } else {
                    return false;
                }
            case BTN:
                if (Arrays.asList(matrix.perc(47)).contains(p)) {
                    return true;
                } else {
                    return false;
                }
            case CO:
                if (Arrays.asList(matrix.perc(24)).contains(p)) {
                    return true;
                } else {
                    return false;
                }
            case MP:
                if (Arrays.asList(matrix.perc(18)).contains(p)) {
                    return true;
                } else {
                    return false;
                }
            case UTG:
                if(Arrays.asList(matrix.perc(14)).contains(p)){
                    return true;
                }
                else return false;

            default:
                return false;
        }

    }

}
