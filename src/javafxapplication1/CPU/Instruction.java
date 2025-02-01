/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Jorge
 */
public enum Instruction {
    LOAD_A (2),
    LOAD_B (1),
    LOAD_C (3),
    LOAD_D (0),
    STORE_A (4),
    STORE_B (5),
    STORE_C (6),
    STORE_D (7),
    ADD (8),
    SUB (9),
    JUMP (10),
    JUMP_NEG (11),
    JUMP_ZRO (12),
    JUMP_ABV (13),
    JUMP_BLW (14),
    HALT (15);
    private  int value;
     private static final  Map<Integer, Instruction> lookup = new HashMap <>();
     static { for (Instruction inst:Instruction.values()) {
                    lookup.put(inst.getValue(),inst);
     }
     }
     
    private Instruction(int value)
    {
        this.value=value;
    }

    public int getValue()
    {
        return(value);
    }
    public static Instruction fromValue (int value) {
    
    Instruction instruction =lookup.get(value);
    return instruction;
    }
    public static String printHash (){
        return lookup.toString();
    }
            
    
}

