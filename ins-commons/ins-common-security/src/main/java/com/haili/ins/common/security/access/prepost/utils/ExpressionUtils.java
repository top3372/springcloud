package com.haili.ins.common.security.access.prepost.utils;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;

public final class ExpressionUtils {
    public ExpressionUtils() {
    }

    public static boolean evaluateAsBoolean(Expression expr, EvaluationContext ctx) {
        try {
            return (Boolean) expr.getValue(ctx, Boolean.class);
        } catch (EvaluationException var3) {
            throw new IllegalArgumentException("Failed to evaluate expression '" + expr.getExpressionString() + "'", var3);
        }
    }
}