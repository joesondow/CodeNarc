/*
 * Copyright 2009 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codenarc.rule.basic

import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule
import org.codenarc.util.AstUtil
import org.codehaus.groovy.ast.expr.MethodCallExpression

/**
 * This rule detects when the equals(Object) method is called directly in code instead of using the == or != operator. A groovier way to express this: a.equals(b) is this: a == b and a groovier way to express : !a.equals(b) is : a != b
 *
 * @author 'Hamlet D'Arcy'
 * @version $Revision: 24 $ - $Date: 2009-01-31 13:47:09 +0100 (Sat, 31 Jan 2009) $
 */
class ExplicitCallToEqualsMethodRule extends AbstractAstVisitorRule {
    String name = 'ExplicitCallToEqualsMethod'
    int priority = 2
    Class astVisitorClass = ExplicitCallToEqualsMethodAstVisitor
}

class ExplicitCallToEqualsMethodAstVisitor extends AbstractAstVisitor {

    def void visitMethodCallExpression(MethodCallExpression call) {
        if (AstUtil.isMethodNamed(call, "equals", 1)) {
            addViolation call
        }
        super.visitMethodCallExpression call
    }
}
