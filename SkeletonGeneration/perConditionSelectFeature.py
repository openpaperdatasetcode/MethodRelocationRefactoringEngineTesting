import json
from typing import List, Dict, Any
def is_method_static(data):
    """
    判断method的access_modifier是否包含"static"
    """
    try:
        method = data.get("method", {})
        access_modifier = method.get("access_modifier", "")
        return "static" in access_modifier
    except (AttributeError, TypeError) as e:
        print(f"解析数据出错: {e}")
        return False

def save_to_json(data_list: List[Dict[str, Any]], output_file: str):
    """
    将数据保存为JSON文件
    """
    try:
        with open(output_file, 'w', encoding='utf-8') as file:
            json.dump(data_list, file, ensure_ascii=False, indent=2)
        print(f"成功保存 {len(data_list)} 条数据到 {output_file}")
    except Exception as e:
        print(f"保存文件时出错: {e}")

def process_multiple_data(data_list):
    """
    批量处理多个数据
    """
    results = []
    for i, data in enumerate(data_list):
        is_static = is_method_static(data)
        results.append({
            "index": i,
            "is_static": is_static,
            "access_modifier": data.get("method", {}).get("access_modifier", "unknown")
        })
    return results

# results = process_multiple_data(data_list)


def contains_super_keyword(item: Dict[str, Any]) -> bool:
    """
    判断features中是否包含super关键字
    """
    try:
        method = item.get("method", {})
        features = method.get("features", [])

        # 检查features列表中是否包含"super"
        for feature in features:
            if isinstance(feature, str) and "super" in feature.lower():
                return True

        return False
    except Exception as e:
        print(f"判断super关键字时出错: {e}")
        return False


def contains_non_static_inner_classes(item: Dict[str, Any]) -> bool:
    """
    检查方法体是否包含非静态内部类
    """
    try:
        method = item.get("method", {})
        features = method.get("features", [])

        # 检查特征中是否包含内部类相关的关键字
        inner_class_indicators = ["inner class", "non-static inner", "member inner class"]
        for feature in features:
            if isinstance(feature, str):
                feature_lower = feature.lower()
                for indicator in inner_class_indicators:
                    if indicator in feature_lower:
                        return True
        return False
    except:
        return False


def accesses_enclosing_instance(item: Dict[str, Any]) -> bool:
    """
    检查方法体是否访问外部类的封闭实例
    """
    try:
        method = item.get("method", {})
        features = method.get("features", [])

        # 检查特征中是否包含访问外部实例的指示器
        enclosing_indicators = [
            "outer.this", "enclosing instance", "outer class instance",
            "this$", "access$"  # Java编译器生成的访问外部类成员的语法
        ]

        for feature in features:
            if isinstance(feature, str):
                feature_lower = feature.lower()
                for indicator in enclosing_indicators:
                    if indicator in feature_lower:
                        return True
        return False
    except:
        return False


def has_illegal_generic_references(item: Dict[str, Any]) -> bool:
    """
    检查泛型中是否有非法引用
    """
    try:
        source_class = item.get("source_class", {})
        target_class = item.get("target_class", {})
        method = item.get("method", {})

        # 检查类和方法是否包含泛型
        source_type = source_class.get("type", "").lower()
        target_type = target_class.get("type", "").lower()
        method_features = method.get("features", [])

        # 如果类或方法涉及泛型，但存在原始类型使用
        if "generic" in source_type or "generic" in target_type:
            for feature in method_features:
                if isinstance(feature, str) and "raw type" in feature.lower():
                    return True

        # 检查特征中是否有泛型相关的错误指示器
        generic_error_indicators = [
            "unchecked cast", "type safety", "generic array creation",
            "cannot be converted", "incompatible types"
        ]

        for feature in method_features:
            if isinstance(feature, str):
                feature_lower = feature.lower()
                for indicator in generic_error_indicators:
                    if indicator in feature_lower:
                        return True
        return False
    except:
        return False


def has_conflicting_signature(item: Dict[str, Any]) -> bool:
    """
    检查目标类中是否有冲突的方法签名
    """
    try:
        method = item.get("method", {})
        features = method.get("features", [])

        # 检查特征中是否有冲突签名的指示器
        conflict_indicators = [
            "method conflict", "signature conflict", "duplicate method",
            "already defined", "method exists"
        ]

        for feature in features:
            if isinstance(feature, str):
                feature_lower = feature.lower()
                for indicator in conflict_indicators:
                    if indicator in feature_lower:
                        return True
        return False
    except:
        return False
def references_non_type_variables(item: Dict[str, Any]) -> bool:
    """
    检查方法是否引用目标类中解析为非类型变量的类型绑定
    """
    try:
        method = item.get("method", {})
        features = method.get("features", [])

        # 检查特征中是否有类型变量相关的错误
        type_variable_indicators = [
            "type variable", "type binding", "unresolved type",
            "cannot be resolved", "type parameter"
        ]

        for feature in features:
            if isinstance(feature, str):
                feature_lower = feature.lower()
                for indicator in type_variable_indicators:
                    if indicator in feature_lower and "error" in feature_lower:
                        return True
        return False
    except:
        return False

def is_annotation_or_interface_method(item: Dict[str, Any]) -> bool:
    """
    检查封闭类型是否是注解或没有默认实现的接口方法
    """
    try:
        source_class = item.get("source_class", {})
        method = item.get("method", {})

        source_type = source_class.get("type", "").lower()
        source_modifier = source_class.get("modifier", "").lower()
        source_features = source_class.get("feature", [])

        # 检查是否是注解
        if "annotation" in source_type:
            return True

        # 检查是否是接口方法但没有默认实现
        if "interface" in source_type:
            method_features = method.get("features", [])
            has_default_impl = any("default" in str(feat).lower() for feat in method_features)
            if not has_default_impl:
                return True

        return False
    except:
        return False

def filter_static_structures(input_file, output_file, max_count=100000):
    """
    读取前10W个结构，过滤掉包含"static"的结构
    """
    with open(input_file, 'r', encoding='utf-8') as f:
        data = json.load(f)

    # 如果数据是列表形式，取前10W个
    if isinstance(data, list):
        data = data[:max_count]

    # 过滤掉包含"static"的结构
    filtered_data = []
    final_count = 0

    for item in data:

        method = item.get("method", {})
        access_modifier = method.get("access_modifier", "").lower()
        # 检查是否包含"static"
        # item_str = json.dumps(item).lower()
        if "static" in access_modifier:
            continue
        # 检查是否包含"abstract"
        if "abstract" in access_modifier:
            continue
        # 检查是否包含"native"
        if "native" in access_modifier:
            continue
        if "synchronized" in access_modifier:
            continue

        method_type = method.get("type", "").lower()
        if "recursion" in method_type:
            continue
        if "constructor" in method_type:
            continue

        result = contains_super_keyword(item)
        if result == "True":
            continue

        if is_annotation_or_interface_method(item):
            continue
            # 条件11: 方法体不包含非静态内部类
        if contains_non_static_inner_classes(item):
            continue
            # 条件12: 方法体不访问外部类的封闭实例
        if accesses_enclosing_instance(item):
            continue
            # 条件13: 没有非法的泛型引用
        if has_illegal_generic_references(item):
            continue
            # 条件14: 目标类中没有冲突的方法签名
        if has_conflicting_signature(item):
            continue
            # 条件15: 方法不引用非类型变量的类型绑定
        if references_non_type_variables(item):
            continue
        final_count += 1
        filtered_data.append(item)

    print(f"总共处理: {len(data)} 个结构")
    print(f"包含static的结构: {final_count} 个")
    print(f"剩余结构: {len(filtered_data)} 个")

    # 保存结果
    with open(output_file, 'w', encoding='utf-8') as f:
        json.dump(filtered_data, f, ensure_ascii=False, indent=2)

    return filtered_data

# 使用示例
filter_static_structures('exhaustive_test_cases1.json', 'perCoditionSelectFeatrue.json', 100000)