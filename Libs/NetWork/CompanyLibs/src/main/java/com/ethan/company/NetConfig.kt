package com.ethan.company

object NetConfig {
    // https://rizz-api.aime-aiportrait.com 正式环境
    // https://rizz-api-test.aime-aiportrait.com 测试环境
    // http://rizz-api-local.aime-aiportrait.com 内网
    const val STS_SERVER_DOMAIN = BuildConfig.stsServerDomain
    const val SHARE_URL = BuildConfig.shareUrl
    const val PRODUCT_ID = 8193
    const val STS_TIME = 5 * 60L

    // 谷歌登录
    const val GOOGLE_LOGIN = "/ven-app/google/app-login"
    // 获取游客信息
    const val GUEST_LOGIN= "/ven-app/guest-login"
    //登出
    const val LOGIN_OUT = "/ven-app/login-out"
    //用户订阅信息与积分查询
    const val SUB_QUERY = "/ven-app/user-subscription"
    //积分明细
    const val POINT_RECORD = "/ven-app/permission/record"

    //Feedback
    const val FEEDBACK_URL = "https://support.tenorshare.com/"
    const val FEEDBACK_REPORT = "api/v1/ticket/feedback"

    //隐私协议
    const val PRIVACY_POLICY = "http://cbs.hitpaw.com/go?pid=8802&a=p"
    //用户协议
    const val TERMS_OF_USE = "http://cbs.hitpaw.com/go?pid=8802&a=tc"


    // Ai扫描分割
    const val REMOVE_OBJECT_SEG = "api/v2/tourist/from_site/android/interact-seg"
    const val REMOVE_OBJECT_SEG_VIP = "api/v2/vip/from_site/android/interact-seg"

    // ai 纹身的皮肤分割
    const val AI_TATTOO_SKIN_SEG = "/api/v2/image/body-parsing"


    // 查询任务状态
    const val TASK_STATUS = "/ven-app/task-status"
    // 查询任务列表
    const val TASK_LIST = "/ven-app/task-list"
    // 删除任务
    const val DELETE_TASK = "/ven-app/del-task"


    // 订单上报
    const val SUBS_REPORT = "/ven-app/android/report-orders"

    // ddcProductID 测试环境 // todo 上线前注释这个
//   const val YEAR = "90000167"
//   const val WEEK = "90000166"
//   const val CBS_ID_POINTS_600 = "90000168"
//   const val CBS_ID_POINTS_1800 = "90000169"
//   const val CBS_ID_POINTS_18000 = "90000170"

    // ddcProductID 生产环境 // todo 上线前使用这个ddcId
      const val YEAR = "106295"
      const val WEEK = "106294"
      const val CBS_ID_POINTS_600 = "106296"
      const val CBS_ID_POINTS_1800 = "106297"
      const val CBS_ID_POINTS_18000 = "106298"


    const val isTest = false
    const val InternalTester = false // todo 上线前改为false

    //---------------------------------------------------------------OSS和素材后台----------------------------------------------------------------------

    const val STS_SERVER = "/app/security-token" // OSS密钥

    // 美西OSS加速地址
    const val OSS_ENDPOINT = "https://oss-accelerate.aliyuncs.com"

    // 美西OSS客户端地址
    const val AMERICA_OSS_ENDPOINT = "https://oss-us-east-1.aliyuncs.com"

    // 桶名
    const val AMERICA_BUCKET_NAME = "ai-hitpaw-us"
    const val INTERNATIONAL_BUCKET_NAME = "ai-hitpaw-us"

    const val AMERICA_HTTPS = "https://ai-hitpaw-us.oss-us-east-1.aliyuncs.com/"
    const val ACCELERATE_AMERICA_HTTPS = "https://ai-hitpaw-us.oss-accelerate.aliyuncs.com/"
    const val ACCELERATE_INTERNATIONAL_AMERICA_HTTPS = "https://ai-hitpaw-us.oss-accelerate.aliyuncs.com/"

    // 返回的域名
    const val SHANG_HAI_OSS_DOMAIN = "hitpaw-apps.oss-cn-shanghai.aliyuncs.com"
    const val AMERICA_OSS_DOMAIN = "ai-hitpaw-us.oss-us-east-1.aliyuncs.com"

    // 需要替换的加速域名
    const val ACCELERATE_DOMAIN = "hitpaw-apps.oss-accelerate.aliyuncs.com"
    const val ACCELERATE_AMERICA_DOMAIN = "ai-hitpaw-us.oss-accelerate.aliyuncs.com" //

    // 违禁词列表
    const val FORBIDDEN_WORDS = "template/photo_enhancer_nsfw_key_word.json"
    const val FORBIDDEN_WORDS_SPREAD_SOURCE = "template/photo_enhancer_nsfw_key_word2.json"

    var timeout = 60L // HTTP请求超时时间(firebase控制)


    // 素材后台 生产
    const val BASE_TEST_PAG_URL = "https://material.hitpaw.com" //

    // 素材管理后台接口
    const val PAG_API = "/api/client/getmaterial"  //

    // 素材管理,根据eft找到分类id
    const val GET_PALETTE_TYPES = "/api/client/getpalettetypes" //

    //视频编清晰
    // const val VIDEO_ENHANCE= "/ven-app/video-enhancer-export" //旧
    const val VIDEO_ENHANCE= "/ven-app/v2/video-enhancer-export" //新
    const val AI_HUMAN= "/ven-app/digital-human" //新
    const val GET_VE_RESULUT= "/ven-app/task-status" //
    // feedback
    const val URL_BASE_UPLOAD = "https://integrated.tenorshare.com/"
    const val URL_LOG_UPLOAD = "api/v1/ticket/upload"

    //抠像
    const val REMOVE_BG= "/ven-app/video-matting" //视频抠像

    //赠送积分接口
    const val GIFT_COINS= "/ven-app/gift-coins"
}