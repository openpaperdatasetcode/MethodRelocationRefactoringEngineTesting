package refactoring.test;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

// Target record class: public modifier, static nested class (target_feature)
public record TargetClass(String id, String value) {
    // Static nested record class (target_inner_rec for method target)
    public static record TargetInnerRecord(Integer code, String message) {
        // Super constructor invocation in compact constructor
        public TargetInnerRecord {
            // Implicit super constructor invocation for record
            code = code == null ? 0 : code;
        }
    }
}

// Source record class: protected modifier, same package as target, empty features
protected record SourceClass(String data) {
    // Inner record class (source_inner_rec for method_position)
    protected record SourceInnerRecord(String innerData) {
        // Instance method: protected access, void return type, target parameter (per_condition)
        protected void refactorMethod(TargetClass.TargetInnerRecord targetParam) {
            // Variable call feature
            String varCall = targetParam.message();
            int code = targetParam.code();

            // Raw type feature
            List rawList = new ArrayList();
            rawList.add(varCall);

            try {
                // SQLException feature
                if (code < 0) {
                    throw new SQLException("Invalid code: " + code);
                }
                
                // Return statement feature
                if (varCall.isBlank()) {
                    return;
                }
                
                // Super constructor invocation (explicit in record context)
                TargetClass.TargetInnerRecord newInnerRecord = new TargetClass.TargetInnerRecord(1, "success");
            } catch (SQLException e) {
                // No new exception thrown (only catch existing SQLException, no new Exception instantiation)
                System.err.println(e.getMessage());
            }
        }
    }
}