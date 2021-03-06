package com.apress.projpa2;

import java.util.List;
import java.util.Vector;

import javax.persistence.Tuple;

import org.apache.commons.lang.builder.*;

import examples.model.Address;
import examples.model.Department;
import examples.model.Employee;
import examples.model.Phone;
import examples.model.Project;

/**
 * Pro JPA 2
 */
public class ProJPAUtil {

    public static void printResult(Object result) throws Exception {
        if (result == null) {
            System.out.print("NULL");
        } else if (result instanceof Object[]) {
            Object[] row = (Object[]) result;
            System.out.print("[");
            for (int i = 0; i < row.length; i++) {
                printResult(row[i]);
            }
            System.out.print("]");
            System.out.println("\nTotal records: " + row.length);
        } else if (result instanceof Long ||
            result instanceof Double ||
            result instanceof String) {
            System.out.print(result.getClass().getName() + ": " + result);
        } else if (result instanceof List) {
            List list = (List) result;
            for (Object item : list) {
                if (item instanceof Address || 
                    item instanceof Department ||
                    item instanceof Employee ||
                    item instanceof Phone ||
                    item instanceof Project) {
                        System.out.println(item);
                }
                if (item instanceof Tuple) {
                    Tuple tuple = (Tuple) item;
                    List elems = tuple.getElements();
                    for (Object elm : elems) {
                        System.out.print(elm.toString());
                    }
//                    System.out.println(tuple.toString());
                }
            }
//            System.out.print(ReflectionToStringBuilder.toString(result, ToStringStyle.SHORT_PREFIX_STYLE));
            System.out.println("\nTotal records: " + list.size());
        } else if (result instanceof Vector) {
            Vector vec = (Vector) result;
            System.out.print(ReflectionToStringBuilder.toString(result, ToStringStyle.SHORT_PREFIX_STYLE));
            System.out.println("\nTotal records: " + vec.size());
        } else {
            System.out.print(ReflectionToStringBuilder.toString(result, ToStringStyle.SHORT_PREFIX_STYLE));
        }
        System.out.println();
    }
}