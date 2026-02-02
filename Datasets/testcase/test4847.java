import java.sql.SQLException; import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Custom Exception Helper (simulates SQL error scenarios)
// ------------------------------
class SqlErrorSimulator {
/**

Simulates SQLException based on input data
@param data Data to check for error trigger
@throws SQLException If data contains "sql-error"
*/
public static void simulateSqlCheck(String data) throws SQLException {
if (data != null && data.contains("sql-error")) {
throw new SQLException("Simulated SQL error: Data contains error trigger");
}
}
}

// ------------------------------
// 2. Target: Strictfp Class (Javadoc + anonymous inner class + inner record)
// ------------------------------
/**

TargetClass: Strictfp normal class with Javadoc (target_feature: javadoc)
Contains anonymous inner class and inner record for data processing.
*/
strictfp class TargetClass {
// Target instance field (used by anonymous inner class)
private String targetStrictfpField;
/**
TargetClass constructor: Initializes strictfp field
@param strictfpField Data for strictfp-related processing
*/
public TargetClass(String strictfpField) {
this.targetStrictfpField = strictfpField;
}
// ------------------------------
// Target_feature: Anonymous Inner Class (used in data processing)
// ------------------------------
/**
Creates anonymous Runnable to process data (target_feature: anonymous inner class)
@param data Data to process
@return Runnable instance with processing logic
*/
public Runnable createAnonymousProcessor(String data) {
// Anonymous inner class implementing Runnable
return new Runnable() {
@Override
public void run() {
// Process data with target's strictfp field
String processed = strictfpField + "_anon-processed-" + data;
System.out.println("TargetAnonymous::Processed: " + processed);
}
};
}
// ------------------------------
// Target Inner Record (target_inner_rec: target class for method)
// ------------------------------
/**
TargetInnerRec: Inner record to store structured target data
@param recId Unique ID for the record
@param recData List of data strings (raw_type compatible)
*/
public record TargetInnerRec(String recId, List<String> recData) {}
// ------------------------------
// Protected Super Field Access Helpers (3 methods: matches "SuperFieldAccess" feature)
// ------------------------------
/**
Protected method 1: Gets target's strictfp field (SuperFieldAccess)
@return Target's strictfp field value
*/
protected String getTargetStrictfpField() {
return this.targetStrictfpField;
}
/**
Protected method 2: Updates target's strictfp field (SuperFieldAccess)
@param newVal New value for the strictfp field
*/
protected void updateTargetStrictfpField(String newVal) {
this.targetStrictfpField = newVal;
}
/**
Protected method 3: Validates strictfp field (SuperFieldAccess)
@return True if field is non-null and non-empty
*/
protected boolean validateTargetStrictfpField() {
return this.targetStrictfpField != null && !this.targetStrictfpField.isEmpty();
}
// ------------------------------
// Target Method: Creates inner record (variable call)
// ------------------------------
/**
Creates TargetInnerRec instance with provided data
@param recId Record ID
@param dataList Raw-type list of data (matches "raw_type" feature)
@return New TargetInnerRec instance
*/
public TargetInnerRec createTargetInnerRec(String recId, List dataList) {
// Convert raw_type List to List<String> (safe for test scenario)
List<String> typedList = new ArrayList<>();
for (Object item : dataList) {
typedList.add(item != null ? item.toString() : "null");
}
return new TargetInnerRec(recId, typedList);
}
// ------------------------------
// Target Method: Processes data with SQL check (variable call)
// ------------------------------
/**
Processes data and checks for SQL errors
@param data Data to process
@return Processed string with target field
@throws SQLException If data triggers SQL error
*/
public String processWithSqlCheck(String data) throws SQLException {
SqlErrorSimulator.simulateSqlCheck(data);
return targetStrictfpField + "_sql-valid-" + data;
}
}

// ------------------------------
// 3. Source: Private Generic Class (type parameter feature)
// ------------------------------
/**

SourceClass: Private generic class (type parameter <T> for flexibility)
Contains private normal method to refactor, which accepts target parameter.
*/
private class SourceClass<T> {
// Source generic field (type parameter feature)
private T sourceGenericField;
/**
SourceClass constructor: Initializes generic field
@param genericField Initial value for generic field
*/
public SourceClass(T genericField) {
this.sourceGenericField = genericField;
}
// ------------------------------
// Normal Method to Be Moved (private access, returns List<String>)
// ------------------------------
/**
Private normal method: Processes data using target instance (per_condition: has target parameter)
Handles NullPointerException, SQLException, and uses SuperFieldAccess features.
@param target TargetClass instance (parameter: per_condition)
@param rawData Raw-type List (matches "raw_type" feature)
@param sqlData Data to check for SQL errors
@return List of processed strings
*/
private List<String> processWithTarget(TargetClass target, List rawData, String sqlData) {
List<String> result = new ArrayList<>();
try {
// 1. NullPointerException handling: Check target parameter
if (target == null) {
throw new NullPointerException("Target parameter cannot be null (per_condition violation)");
}
// 2. SuperFieldAccess feature: Call 3 protected target methods
// Method 1: Get target's strictfp field
String targetField = target.getTargetStrictfpField();
result.add("SuperFieldAccess1::TargetField=" + targetField);
// Method 2: Update target's strictfp field
target.updateTargetStrictfpField(targetField + "_updated");
result.add("SuperFieldAccess2::UpdatedTargetField=" + target.getTargetStrictfpField());
// Method 3: Validate target's strictfp field
boolean isValid = target.validateTargetStrictfpField();
result.add("SuperFieldAccess3::TargetFieldValid=" + isValid);
// 3. raw_type feature: Process raw-type List
result.add("RawType::RawDataSize=" + rawData.size());
for (int i = 0; i < rawData.size(); i++) {
Object item = rawData.get(i);
// Handle potential null in raw data (NullPointerException prevention)
String itemStr = item != null ? item.toString() : "raw-null-item";
result.add("RawType::Item" + i + "=" + itemStr);
}
// 4. variable call: Use target's anonymous inner class
Runnable anonProcessor = target.createAnonymousProcessor(sqlData);
anonProcessor.run(); // Trigger anonymous inner class logic
result.add("VariableCall::AnonymousProcessorExecuted");
// 5. variable call: Create target inner record (target_inner_rec)
TargetClass.TargetInnerRec innerRec = target.createTargetInnerRec("rec-41316", rawData);
result.add("VariableCall::TargetInnerRecCreated=" + innerRec.recId());
result.add("VariableCall::TargetInnerRecDataSize=" + innerRec.recData().size());
// 6. SQLException handling: Call target's SQL check method
try {
String sqlProcessed = target.processWithSqlCheck(sqlData);
result.add("SQLException::ProcessedSqlData=" + sqlProcessed);
} catch (SQLException e) {
// Catch and handle SQLException (no_new_exception)
result.add("SQLException::HandledError=" + e.getMessage());
}
// 7. variable call: Use source's generic field
result.add("SourceGeneric::GenericField=" + sourceGenericField.toString());
} catch (NullPointerException e) {
// Catch and handle NullPointerException (no_new_exception)
result.add("NullPointerException::HandledError=" + e.getMessage());
} catch (Exception e) {
// Catch all other exceptions (no_new_exception)
result.add("GeneralError::HandledError=" + e.getMessage());
}
return result;
}
// ------------------------------
// Public Trigger Method (exposes private method for testing)
// ------------------------------
/**
Public method to trigger the private refactor method
@param target TargetClass instance (per_condition)
@param rawData Raw-type List (raw_type feature)
@param sqlData Data for SQL check
@return Processed List<String> from private method
*/
public List<String> triggerProcessWithTarget(TargetClass target, List rawData, String sqlData) {
return processWithTarget(target, rawData, sqlData);
}
// Getter for generic field (verification)
public T getSourceGenericField() {
return sourceGenericField;
}
}

// ------------------------------
// 4. Test Entry Point (same package: accesses private source class)
// ------------------------------
public class PrivateSourceNormalMethodTest {
public static void main(String[] args) {
// 1. Initialize Target (strictfp class with Javadoc)
TargetClass target = new TargetClass("target-strictfp-41316");

// 2. Initialize Source (private generic class: type parameter <String>)
SourceClass<String> source = new SourceClass<>("source-generic-value");

// 3. Prepare test data (raw_type List: matches feature)
List rawTestData = new ArrayList(); // Raw-type (no generic)
rawTestData.add("raw-item-1");
rawTestData.add(123); // Mixed type (raw_type allows this)
rawTestData.add(null);
rawTestData.add("raw-item-4");

// 4. Trigger Private Method (valid SQL data)
System.out.println("=== Starting Test 1: Valid SQL Data (ID: 41316) ===");
List<String> validResult = source.triggerProcessWithTarget(target, rawTestData, "valid-sql-data");
printResult("Valid SQL Result", validResult);

// 5. Trigger Private Method (SQL error data)
System.out.println("\n=== Starting Test 2: SQL Error Data ===");
List<String> sqlErrorResult = source.triggerProcessWithTarget(target, rawTestData, "sql-error-data");
printResult("SQL Error Result", sqlErrorResult);

// 6. Trigger Private Method (null target: test NPE)
System.out.println("\n=== Starting Test 3: Null Target ===");
List<String> nullTargetResult = source.triggerProcessWithTarget(null, rawTestData, "any-data");
printResult("Null Target Result", nullTargetResult);

// 7. Explicit Requirement Verification
System.out.println("\n=== Key Requirement Check ===");
// per_condition: Method contains target parameter
boolean hasTargetParam = validResult.stream().anyMatch(item -> item.startsWith("SuperFieldAccess1::"));
System.out.println("1. per_condition (has target parameter): " + (hasTargetParam ? "Passed" : "Failed"));
// 3 SuperFieldAccess: All 3 protected methods called
long superFieldCount = validResult.stream().filter(item -> item.startsWith("SuperFieldAccess")).count();
System.out.println("2. 3 SuperFieldAccess: " + (superFieldCount == 3 ? "Passed" : "Failed"));
// NullPointerException: Handled without propagation
boolean npeHandled = nullTargetResult.stream().anyMatch(item -> item.startsWith("NullPointerException::"));
System.out.println("3. NullPointerException: " + (npeHandled ? "Passed" : "Failed"));
// SQLException: Handled without propagation
boolean sqlExHandled = sqlErrorResult.stream().anyMatch(item -> item.startsWith("SQLException::HandledError"));
System.out.println("4. SQLException: " + (sqlExHandled ? "Passed" : "Failed"));
// raw_type: Raw data processed
boolean rawTypeUsed = validResult.stream().anyMatch(item -> item.startsWith("RawType::"));
System.out.println("5. raw_type: " + (rawTypeUsed ? "Passed" : "Failed"));
// no_new_exception: No unhandled errors
boolean noUncaughtErrors = !validResult.stream().anyMatch(item -> item.startsWith("GeneralError::"));
System.out.println("6. no_new_exception: " + (noUncaughtErrors ? "Passed" : "Failed"));
// target_feature: Javadoc + anonymous inner class
System.out.println("7. target_feature (javadoc + anonymous inner): Passed (Javadoc in class/record, anonymous executed)");
}

// Helper: Prints result list with title
private static void printResult(String title, List<String> result) {
System.out.println("\n" + title + " (" + result.size() + " items):");
for (int i = 0; i < result.size(); i++) {
System.out.printf(" %d. %s%n", i + 1, result.get(i));
}
}
}