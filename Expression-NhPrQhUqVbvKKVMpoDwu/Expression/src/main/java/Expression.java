/**
 * This class can be used to build simple arithmetic expression
 * with binary operator +,-,* and involving one variable 'x'.
 *
 * The expression can be
 * 1) evaluated by replacing the variable x with a specific value
 * 2) derivated to obtain a new expression
 *
 * You must modify this class to make it work
 * You can/should extend this class with inner classes the way you want.
 * You can also modify it but you are not allowed to modify the signature
 * of existing methods
 *
 * As a reminder, the formulas for the derivations as are followed
 *  - (f + g)' = f' + g'
 *  - (f*g)' = f'g + fg'
 *  - (x)' = 1
 *  - (C)' = 0 with C a constant
 */
public abstract class Expression {

    /**
     * Creates the basic variable expression 'x'
     * @return the expression 'x'
     */
    public static Expression x() {
         return new Expression() {
             @Override
             public double evaluate(double xValue) {
                 return xValue;
             }

             @Override
             public Expression derivate() {
                 return value(1);
             }
         };
    }

    /**
     * Creates the basic constant expression 'v'
     * @return the expression 'v'
     */
    public static Expression value(double v) {
         return new Expression() {
             @Override
             public double evaluate(double xValue) {
                 return v;
             }

             @Override
             public Expression derivate() {
                 return value(0);
             }
         };
    }

    /**
     * Creates the binary expression 'this + r'
     * @param r the right operator
     * @return the binary expression 'this + r'
     */
    public Expression plus(Expression r) {
         Expression fristTerm = this;
         return new Expression() {
             @Override
             public double evaluate(double xValue) {

                 return fristTerm.evaluate(xValue) + r.evaluate(xValue);
             }

             @Override
             public Expression derivate() {
                 return fristTerm.derivate().plus(r.derivate());
             }
         };
    }

    /**
     * Creates the binary expression 'this - r'
     * @param r the right operator
     * @return the binary expression 'this - r'
     */
    public Expression minus(Expression r) {
         Expression firstTerm = this;
         return new Expression() {
             @Override
             public double evaluate(double xValue) {
                 return firstTerm.evaluate(xValue) - r.evaluate(xValue);
             }

             @Override
             public Expression derivate() {
                 return firstTerm.derivate().minus(r.derivate());
             }
         };
    }

    /**
     * Creates the binary expression 'this * r'
     * @param r the right operator
     * @return the binary expression 'this * r'
     */
    public Expression mul(Expression r) {
        Expression firstTerm = this;
         return new Expression() {
             @Override
             public double evaluate(double xValue) {
                 return firstTerm.evaluate(xValue)*r.evaluate(xValue);
             }

             @Override
             public Expression derivate() {
                 return (firstTerm.derivate().mul(r)).plus(firstTerm.mul(r.derivate()));
             }
         };
    }

    /**
     * Evaluate the expression with fixed value for x
     * @param xValue the value taken by x for the evaluation
     * @return the evaluation of the expression considering x=xValue
     */
    public abstract double evaluate(double xValue);

    /**
     * Derivate the expression wrt to 'x'
     * @return the derivative of the expression with respect to 'x'
     */
    public abstract Expression derivate();





}
