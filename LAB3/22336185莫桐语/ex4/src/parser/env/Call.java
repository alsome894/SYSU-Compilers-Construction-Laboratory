package parser.env;

import scanner.token.*;
import parser.type.*;
import java.util.*;
import exceptions.*;

/**
 * 表示一次过程调用，包括过程标识符、签名和环境。
 */
public class Call {
    private Identifier id;
    private ProcedureType signature;
    private Env env;

    /**
     * 构造一个过程调用对象。
     * @param id 过程标识符
     * @param params 参数类型列表
     * @param env 当前环境
     */
    public Call(Identifier id, ArrayList<Type> params, Env env) {
        this.id = id;
        this.signature = ProcedureType.rawSig(params);
        this.env = env;
    }

    /**
     * 获取过程标识符。
     * @return 标识符
     */
    public Identifier getId() {
        return id;
    }

    /**
     * 获取过程签名。
     * @return 过程类型
     */
    public ProcedureType getSignature() {
        return signature;
    }

    /**
     * 验证过程调用是否合法。
     * @throws OberonException 如果过程不存在或签名不匹配
     */
    public void verify() throws OberonException {
        Declaration decl = env.getDecl(id.getLexeme());
        if (decl == null) {
            throw new UnknownIdentifier(id);
        }
        if (!(decl.getType() instanceof ProcedureType)) {
            throw new UnexpectedType("procedure", decl.getType(), id.getLine(), id.getColumn());
        }
        ProcedureType procType = (ProcedureType) decl.getType();
        if (!procType.equals(signature)) {
            throw new UnexpectedType(decl.getType(), signature, id.getLine(), id.getColumn());
        }
    }

    /**
     * 判断两个过程调用对象是否相等。
     * @param obj 比较对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Call)) {
            return false;
        }
        ProcedureType other = (ProcedureType) obj;
        return signature.equals(other);
    }

    /**
     * 获取哈希码。
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * 获取过程调用的字符串表示。
     * @return 字符串
     */
    @Override
    public String toString() {
        String result = id + "(";
        for (Declaration param : signature.getParams()) {
            result += param.getType() + ", ";
        }
        if (signature.getParams().size() > 0) {
            result = result.substring(0, result.length() - 2);
        }
        result += ")";
        return result;
    }
}
