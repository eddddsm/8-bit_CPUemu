/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.CPU;

import javafx.scene.shape.Rectangle;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Jorge
 */
public class ControlUnit {

    private ALU alu;
    private CpuRegister register1;
    private CpuRegister register2;
    private CpuRegister register3;
    private CpuRegister register4;

    private CpuRegister instructionRegister;
    private CpuRegister addressRegister;

    private RAM ram;

    private Boolean O_flag;
    private Boolean Z_flag;
    private Boolean N_flag;

    private Rectangle oflag_rec;
    private Rectangle zflag_rec;
    private Rectangle nflag_rec;

    public ControlUnit() {
        this.O_flag = false;
        this.Z_flag = false;
        this.N_flag = false;
    }

    public void setFlagsGUI(Rectangle oflag_rec, Rectangle zflag_rec, Rectangle nflag_rec) {
        this.oflag_rec = oflag_rec;
        this.zflag_rec = zflag_rec;
        this.nflag_rec = nflag_rec;
    }

    public void setALU(ALU alu) {
        this.alu = alu;
    }

    public void setRAM(RAM ram) {
        this.ram = ram;
    }

    public void setRegisters(CpuRegister register1, CpuRegister register2, CpuRegister register3, CpuRegister register4) {
        this.register1 = register1;
        this.register2 = register2;
        this.register3 = register3;
        this.register4 = register4;
    }

    public void setFlags(Boolean o_flag, Boolean z_flag, Boolean n_flag) {
        this.O_flag = o_flag;
        this.Z_flag = z_flag;
        this.N_flag = n_flag;
    }

    public void resetFlags() {
        this.O_flag = false;
        this.Z_flag = false;
        this.N_flag = false;
    }

    public boolean isFlagOn() {
        return this.O_flag || this.Z_flag || this.N_flag;
    }

    public boolean getOflag() {
        return this.O_flag;
    }

    public boolean getZflag() {
        return this.Z_flag;
    }

    public boolean getNflag() {
        return this.N_flag;
    }

    public void updateFlagsGUI() {
        if (this.O_flag) {
            this.oflag_rec.setStyle("-fx-fill: #ff9f00;");
        } else {
            this.oflag_rec.setStyle("-fx-fill: #96a6b5;");
        }
        if (this.Z_flag) {
            this.zflag_rec.setStyle("-fx-fill: #ff9f00;");
        } else {
            this.zflag_rec.setStyle("-fx-fill: #96a6b5;");
        }
        if (this.N_flag) {
            this.nflag_rec.setStyle("-fx-fill: #ff9f00;");
        } else {
            this.nflag_rec.setStyle("-fx-fill: #96a6b5;");
        }
    }

    public NumberingSystem swapAddressRegisterBase() {
        return this.addressRegister.swapBase();
    }

    public NumberingSystem swapInstructionRegisterBase() {
        return this.instructionRegister.swapInstBase();
    }

    public void setInstructionRegister(CpuRegister instructionRegister) {
        this.instructionRegister = instructionRegister;
    }

    public void setAddressRegister(CpuRegister addressRegister) {
        this.addressRegister = addressRegister;
    }

    public void updateGUI() {
        this.addressRegister.update();
        this.instructionRegister.update();
    }

    public static Instruction getOpcode(Register reg) {
        int value = Byte.toUnsignedInt(reg.getValue());
        value = (value>>4);
        return Instruction.fromValue(value);
    }

    public static int stringBinaryToInt(String st) {
       return Integer.parseInt(st,2);

    }

    public static String getInstructionAsString(NumberingSystem ns, Register reg) {
            Instruction opcode = getOpcode(reg);
            String st = reg.getBinaryValueAsString();
            StringBuilder instruction = new StringBuilder(opcode.toString());
            Map<String, String> lookup = new HashMap <>();
                    lookup.put("00","A");
                    lookup.put("01","B");
                    lookup.put("10","C");
                    lookup.put("11","D");
            switch (opcode.getValue()) {
                case 8 : //ADD   
                case 9 : //SUB  
                    String op1 = "" + st.charAt(4) + st.charAt(5);
                    String op2 = "" + st.charAt(6) + st.charAt(7);
                    if (ns == NumberingSystem.OPCbin) {
                     instruction.append(" ").append(op1).append(" ").append(op2);
                     return instruction.toString();
                     }
                    if (ns == NumberingSystem.OPCdec) {
                    instruction.append(" ").append(lookup.get(op1)).append(" ").append(lookup.get(op2));
                    return instruction.toString();
                    }
                case 15: //HATL
                    String op = "" + st.charAt(4) + st.charAt(5) + st.charAt(6) + st.charAt(7);
                    if (ns == NumberingSystem.OPCbin) {
                    instruction.append( " ").append(op);
                    return instruction.toString();
                    }
                    if (ns == NumberingSystem.OPCdec) {
                     byte opnumber = Byte.parseByte(op, 2);
                     instruction.append(" ").append(opnumber);
                     return instruction.toString();
                    }
                default:
                    String opdefault = "" + st.charAt(4) + st.charAt(5) + st.charAt(6) + st.charAt(7);
                    if (ns == NumberingSystem.OPCbin) {
                    instruction.append(" ").append(opdefault);
                    return instruction.toString();
                    }
                    if (ns == NumberingSystem.OPCdec) {
                     instruction.append(" ").append(Integer.parseInt(opdefault,2));   
                    }
            }
        return instruction.toString();
    }

    public void fetch() {
        this.instructionRegister.setHighlight(true);
    }

    public CpuRegister getAddressRegister() {
        return this.addressRegister;
    }

    public CpuRegister getInstructionRegister() {
        return this.instructionRegister;
    }

}