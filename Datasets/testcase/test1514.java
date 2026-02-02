package test;
import java.util.ArrayList;import java.util.List;
// Generic target class with static nested classclass Target<T> {public T value; // Field accessed by sourcepublic static final String STATIC_FIELD = "target_static";
static class Nested {
U nestedValue;
Nested(U value) {this.nestedValue = value;}
String getStringValue() {return nestedValue.toString();}}}
// Final generic source class with member inner classesfinal class Source<T> {class FirstInner {class InnerRec {// Method in source_inner_rec positionprotected List<String> process(Target<T> target) throws IllegalArgumentException {// Type declaration statementTarget.Nested<T> targetNested = new Target.Nested<>(target.value);List<String> result = new ArrayList<>();
// ArrayCreation expression (1 instance)String[] dataArray = new String[1];dataArray[0] = target.STATIC_FIELD;
// Variable call (access target field)result.add(target.value.toString());result.add(targetNested.getStringValue());result.add(dataArray[0]);
// Requires throwsif (target.value == null) {throw new IllegalArgumentException("Target value cannot be null");}
return result;}}}}