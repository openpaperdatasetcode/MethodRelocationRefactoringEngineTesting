import json
import random


def select_from_first_100k(json_data, count=5000):
    """
    从前10万个结构中随机选择指定数量的结构
    """
    # 确保不超过数据长度
    actual_count = min(count, min(100000, len(json_data)))

    # 随机选择5000个索引（从0到99999）
    selected_indices = random.sample(range(min(100000, len(json_data))), actual_count)

    # 提取选中的结构
    return [json_data[i] for i in selected_indices]


# 使用示例
with open('perCoditionSelectFeatrue.json', 'r', encoding='utf-8') as f:
    data = json.load(f)

selected_5000 = select_from_first_100k(data, 5000)

# 保存结果
with open('D:\\aa\第四个论文\GenerateTestCase\perCodition\\noCluster_random5K\\ranSelectFeature.json', 'w', encoding='utf-8') as f:
    json.dump(selected_5000, f, ensure_ascii=False, indent=2)
