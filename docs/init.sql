
create table if not exists `t_thread_pool` (
  `id` int(11) not null auto_increment primary key ,
  `application_id` varchar(32) not null default '' comment '应用key',
  `application_name` varchar(32) not null default '' comment '应用名称',
  `ip` varchar(32) not null default '' comment '服务部署的ip地址',
  `thread_pool_name` varchar(32) not null default '' comment '线程池名称',
  `core_pool_size` int(6) not null default 0 comment '核心线程数',
  `maximum_pool_size` int(6) not null default 0 comment '最大线程数',
  `active_count` int(6) not null default 0 comment '活跃线程数',
  `pool_size` int(6) not null default 0 comment '当前线程池大小',
  `queue_type` varchar(32) not null default '' comment '队列类型',
  `queue_size` int(6) not null default 0 comment '队列大小',
  `remaining_capacity` int(6) not null default 0 comment '队列剩余容量',
  `create_time` timestamp comment '服务上线时间',
  `active_time` timestamp comment '最近一次上报时间',
  `down` int(4) not null default 0 comment '是否下线，0-在线，1-下线'
) engine=innodb default charset=utf8mb4 comment='线程池信息';