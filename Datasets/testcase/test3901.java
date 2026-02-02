package test;
// Interface for target class to implementinterface DataProcessor<T> {void process(T data);}
// Source class: default modifier, no additional featuresclass SourceClass {// Variable call target methodprivate void helperMethod() {}
// Method to test Move Method: instance type, void return, default access// Contains target class parameter (satisfies per_condition)void methodToRefactor(TargetClass<String> targetParam) {// Throw statement (method feature)if (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
// For loop (matches method feature pos: "for")for (int i = 0; i < 2; i++) {// Call target class instance method (matches method_feature)TargetClass<String> result = TargetClass.createTargetInstance(targetParam, "data-" + i);}
// Variable call (method feature)helperMethod();}}
// Target class: public modifier, with type parameter, implements interface, anonymous inner classpublic class TargetClass<T> implements DataProcessor<T> {private T data;
// Constructor for instance creationpublic TargetClass(T data) {this.data = data;}
// Instance method (matches method_feature "target" "instance")public T getData() {return this.data;}
// Static method to create target instance (matches "ClassName.methodName(arguments)")public static <T> TargetClass<T> createTargetInstance(TargetClass<T> template, T newData) {return new TargetClass<>(newData);}
// Implements method from DataProcessor@Overridepublic void process(T data) {this.data = data;}
// Anonymous inner class (target_feature){new Runnable() {@Overridepublic void run() {System.out.println("Processed data: " + data);}};}}