package parser.env;

import java.util.*;
import exceptions.*;
import parser.type.*;

/**
 * 表示一个环境，用于存储声明及其作用域链。
 */
public class Env {

    private Hashtable<String, Declaration> decls;
    protected Env prev;

    /**
     * 创建一个基于当前环境的新嵌套环境。
     * 
     * @param cur 当前环境
     */
    public Env(Env cur) {
        decls = new Hashtable<String, Declaration>();
        prev = cur;
    }

    /**
     * 向环境中添加声明。
     * 
     * @param s 声明的词素
     * @param t 声明对象
     * @throws OberonException 如果已存在同名声明
     */
    public void putDecl(String s, Declaration t) throws OberonException {
        if (decls.containsKey(s.toLowerCase()))
            throw new OberonException();
        decls.put(s.toLowerCase(), t);
    }

    /**
     * 从环境中获取声明。
     * 
     * @param s 声明的词素
     * @return 声明对象，若不存在则返回null
     */
    public Declaration getDecl(String s) {
        String key = s.toLowerCase();
        for (Env e = this; e != null; e = e.prev) {
            Declaration found = e.decls.get(key);
            if (found != null)
                return found;
        }
        return null;
    }
}