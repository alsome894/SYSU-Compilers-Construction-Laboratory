package parser.type;

import java.util.*;
import parser.env.*;
import scanner.token.Identifier;

/**
 * 表示过程类型（函数签名）的类。
 */
public class ProcedureType extends Type {
    ArrayList<Declaration> params;

    /**
     * 构造一个过程类型，指定参数列表。
     */
    public ProcedureType(ArrayList<Declaration> params) {
        super("procedure");
        this.params = new ArrayList<Declaration>();
        for (Declaration param : params) {
            this.params.add(param);
        }
    }

    /**
     * 根据类型列表生成一个过程类型（参数名自动生成）。
     */
    public static ProcedureType rawSig(ArrayList<Type> types) {
        ArrayList<Declaration> params = new ArrayList<Declaration>();
        int i = 0;
        for (Type type : types) {
            params.add(new Declaration(new Identifier("__" + i + "__"), type, null));
            i += 1;
        }
        return new ProcedureType(params);
    }

    /**
     * 获取参数列表。
     */
    public ArrayList<Declaration> getParams() {
        return params;
    }

    /**
     * 判断类型是否相等。
     * 如果参数是AnyType，则返回true。
     * 否则仅当参数也是ProcedureType且参数类型一一对应相等时返回true。
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        if (!(obj instanceof ProcedureType)) {
            return false;
        }
        ProcedureType other = (ProcedureType) obj;
        for (int i = 0; i < params.size(); i += 1) {
            if (!params.get(i).getType().equals(other.params.get(i).getType())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回哈希码。
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * 返回类型的字符串表示。
     */
    @Override
    public String toString() {
        String result = "procedure (";
        for (Declaration param : params) {
            result += param.getType() + ", ";
        }
        if (params.size() > 0) {
            result = result.substring(0, result.length() - 2);
        }
        return result + ")";
    }
}
