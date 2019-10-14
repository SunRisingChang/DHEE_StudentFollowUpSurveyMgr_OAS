/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : dhee_2

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-01 18:15:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `userpassword` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for compary
-- ----------------------------
DROP TABLE IF EXISTS `compary`;
CREATE TABLE `compary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of compary
-- ----------------------------
INSERT INTO `compary` VALUES ('11', '大连华信');
INSERT INTO `compary` VALUES ('12', '大连东软');
INSERT INTO `compary` VALUES ('13', '沈阳中软');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `emp_id` int(20) NOT NULL,
  `company` varchar(20) DEFAULT NULL,
  `job` varchar(20) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `salary` double(20,0) DEFAULT NULL,
  `single_gold` double(20,0) DEFAULT NULL,
  KEY `qwe` (`emp_id`),
  CONSTRAINT `qwe` FOREIGN KEY (`emp_id`) REFERENCES `emp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('22', '大连东软', '体育部', '2017-03-28', '2017-03-08', '21413', '141321');
INSERT INTO `customer` VALUES ('26', '大连华信', '餐饮部', '2017-03-28', '2014-03-28', '123', '123');
INSERT INTO `customer` VALUES ('26', '大连华信', '技术部', '2017-03-23', '2017-03-16', '0', '0');
INSERT INTO `customer` VALUES ('19', '沈阳中软', '外交部', '2017-03-28', '2017-03-28', '1231', '123132');
INSERT INTO `customer` VALUES ('19', '大连东软', '体育部', '2017-03-28', '2017-02-28', '1231', '312313');
INSERT INTO `customer` VALUES ('18', '大连东软', '体育部', '2017-03-28', '2017-03-28', '123', '131231');
INSERT INTO `customer` VALUES ('25', '沈阳中软', '技术部', '2017-03-29', '2017-03-29', '11000', '6000');
INSERT INTO `customer` VALUES ('50', '大连东软', '体育部', '2017-05-11', '2017-05-11', '23424', '4234242');
INSERT INTO `customer` VALUES ('50', '大连东软', '体育部', '2017-05-11', '2017-05-11', '23424', '423424');
INSERT INTO `customer` VALUES ('16', '大连华信', '餐饮部', '2017-05-11', '2017-05-11', '1231', '31231');
INSERT INTO `customer` VALUES ('16', '大连东软', '公关部', '2017-05-11', '2017-05-11', '1231', '3131321');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactdate` date DEFAULT NULL,
  `HR` varchar(255) DEFAULT NULL,
  `fastentrytime` date DEFAULT NULL,
  `post` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `idno` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `recruitmenttype` varchar(255) DEFAULT NULL,
  `skill` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `languageability` varchar(255) DEFAULT NULL,
  `hopesalary` double DEFAULT NULL,
  `workplace` varchar(255) DEFAULT NULL,
  `graduatetime` date DEFAULT NULL,
  `graduateschool` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `telphone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `othercontact` varchar(255) DEFAULT NULL,
  `entryedornot` varchar(255) DEFAULT NULL,
  `entrytime` date DEFAULT NULL,
  `contractperiod` date DEFAULT NULL,
  `uesperiod` varchar(255) DEFAULT NULL,
  `contractrenewal` varchar(255) DEFAULT NULL,
  `welfare` varchar(255) DEFAULT NULL,
  `departuretime` varchar(255) DEFAULT NULL,
  `departurereasons` varchar(255) DEFAULT NULL,
  `enclosure` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('16', '2017-03-17', '王', '2017-03-24', 'no', '23423', 'TT', '234243', '3', '哈萨克族', '挂靠', '翻译', '德语', '八级', '234234', '湖北省-荆州市-监利县', '2017-03-28', '2342', '42342', '中专', '2342', '42342', '423424', 'is', '2017-03-07', '2017-03-17', '2个月', '23423', '42342', '1年', '23424324', '');
INSERT INTO `emp` VALUES ('18', '2017-03-22', '王莹', '2017-03-15', 'no', 'qweq', 'TT', '232123141', '3', '哈尼族', '挂靠', '翻译', '德语', '七级', '12313', '湖北省-孝感市-云梦县', '2017-03-20', '12313', '12313', '中专', '1231', '31231', '13131', 'is', '2017-03-21', '2017-03-17', '1个月', '12312', '3123', '1年', '1231231', '');
INSERT INTO `emp` VALUES ('19', '2017-03-17', '王王', '2017-03-16', 'no', '123', 'TT', '123123', '3', '哈尼族', '自招', '项目管理', '西班牙语', '九级', '123123', '湖北省-孝感市-云梦县', '2017-03-21', '123', '3123', '中专', '12312313', '321313', '123131', 'is', '2017-03-28', '2017-03-08', '3个月', '1231', '32123', '1年', '1231313', '');
INSERT INTO `emp` VALUES ('22', '2017-03-14', 'admin', '2017-03-17', 'is', '张三', 'TT', '12314', '3', '瑶族', '挂靠', '项目管理', '德语', '七级', '2313', '江西省-吉安市-遂川县', '2017-03-07', '23413', '413132', '大专', '2341', '432131', '412313', 'is', '2017-03-14', '2017-03-15', '1个月', '234132', '4132141', '1年', '2314132141', '');
INSERT INTO `emp` VALUES ('25', '2017-03-22', '王莹', '2017-03-31', 'is', 'wy', 'FF', '210122199004267891', '20', '彝族', '自招', 'Java', '英语', '四级', '9000', '辽宁省-沈阳市-沈河区', '2017-03-29', 'LISN', 'JSJ', '大专', '1234666666', '23556778@qq.com', '2424554656', 'is', '2017-04-14', '2017-06-15', '3个月', '3', 'wu', '3年', 'gr', '');
INSERT INTO `emp` VALUES ('26', '2017-03-17', '王莹', '0001-01-01', 'is', 'nihaosrc', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('27', '0001-01-01', 'defult', '0001-01-01', 'is', 'nihao5', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('29', '0001-01-01', 'defult', '0001-01-01', 'is', '你好啊', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('30', '2017-03-15', '王莹', '2017-03-30', 'is', '让人', 'TT', '2323', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('32', '0001-01-01', 'admin', '0001-01-01', 'is', '12345', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('33', '0001-01-01', 'admin', '0001-01-01', 'is', '123456', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('34', '0001-01-01', 'admin', '0001-01-01', 'is', '1234567', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('35', '0001-01-01', 'admin', '0001-01-01', 'is', '12345678', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('36', '0001-01-01', 'admin', '0001-01-01', 'is', '123456789', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('37', '0001-01-01', 'admin', '0001-01-01', 'is', '12345678910', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('38', '0001-01-01', 'defult', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('39', '0001-01-01', 'defult', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('40', '0001-01-01', 'defult', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('41', '0001-01-01', 'defult', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('42', '0001-01-01', 'defult', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('43', '0001-01-01', 'defult', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('44', '0001-01-01', 'defult', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('45', '0001-01-01', 'defult', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('46', '0001-01-01', 'defult', '0001-01-01', 'is', 'admin', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('47', '0001-01-01', 'defult', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('48', '0001-01-01', 'admin123', '0001-01-01', 'is', '', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('49', '0001-01-01', 'admin123', '0001-01-01', 'is', '1111111', 'TT', '', '0', 'defult', 'defult', 'defult', 'defult', 'defult', '0', '--', '0001-01-01', '', '', 'defult', '', '', '', 'is', '0001-01-01', '0001-01-01', 'defult', '', '', 'defult', '', '');
INSERT INTO `emp` VALUES ('50', '2017-05-08', '张琪', '2017-05-09', 'is', '23424', 'TT', '234242', '0', '哈尼族', '自招', '软件测试', '德语', '六级', '2342424', '河南省-南阳市-淅川县', '2017-05-23', '234242', '42342424', '中专', '2342424', '42342424', '2342424', 'is', '2017-05-24', '2017-05-16', '2个月', '23424243', '43242424', '项目合同', '234242432', '');

-- ----------------------------
-- Table structure for hr_user
-- ----------------------------
DROP TABLE IF EXISTS `hr_user`;
CREATE TABLE `hr_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `userpassword` varchar(255) NOT NULL,
  `realname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hr_user
-- ----------------------------
INSERT INTO `hr_user` VALUES ('67', '张琪1', '1234', '张琪');
INSERT INTO `hr_user` VALUES ('68', '王莹', '1234', '王莹1');
INSERT INTO `hr_user` VALUES ('69', '王王', '12345', '王');
INSERT INTO `hr_user` VALUES ('70', 'admin', 'admin', 'admin123');

-- ----------------------------
-- Table structure for jobs
-- ----------------------------
DROP TABLE IF EXISTS `jobs`;
CREATE TABLE `jobs` (
  `comid` int(11) NOT NULL,
  `job` varchar(255) NOT NULL,
  KEY `comid` (`comid`),
  CONSTRAINT `a1` FOREIGN KEY (`comid`) REFERENCES `compary` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jobs
-- ----------------------------
INSERT INTO `jobs` VALUES ('11', '业务部');
INSERT INTO `jobs` VALUES ('11', '技术部');
INSERT INTO `jobs` VALUES ('11', '餐饮部');
INSERT INTO `jobs` VALUES ('12', '媒体部');
INSERT INTO `jobs` VALUES ('12', '体育部');
INSERT INTO `jobs` VALUES ('12', '公关部');
INSERT INTO `jobs` VALUES ('13', '技术部');
INSERT INTO `jobs` VALUES ('13', '外交部');
INSERT INTO `jobs` VALUES ('13', '安保部');
