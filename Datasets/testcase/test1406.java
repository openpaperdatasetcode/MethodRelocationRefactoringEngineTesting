package test;
sealed class SealedSuperClass permits TargetClass {}
strictfp class SourceClass {// Static field for depends_on_static_fieldprivate static final int STATIC_FIELD = 42;
// Anonymous inner class (source feature)Runnable anonInner = new Runnable() {@Overridepublic void run() {}};
// Member inner class (source feature)class SourceInner { // source_inner/**
Method Javadoc
@param param target class parameter (per_condition)
@return base type result*/private int methodToMove(TargetClass<String> param) { // base type return// contains target parameter (per_condition)if (param == null) {return -1;}
// constructor invocationSourceInner inner = new SourceInner();TargetClass<String> target = new TargetClass<>("targetData");
// variable callinner.doSomething();target.useTypeParam();
// with_boundsclass BoundedType<T extends CharSequence> {T value;}BoundedType<String> bounded = new BoundedType<>();bounded.value = param.getTypeParamValue();
// depends_on_static_fieldint staticDep = STATIC_FIELD;
return staticDep; // base type}
private void doSomething() {}}}
non-sealed class TargetClass extends SealedSuperClass { // target_feature: type parameter
private U typeParam;
public TargetClass(U value) {this.typeParam = value;}
public void useTypeParam() {System.out.println(typeParam);}
public U getTypeParamValue() {return typeParam;}}