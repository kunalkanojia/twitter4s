package com.danielasfregola.twitter4s.http.clients.rest.friendships

import com.danielasfregola.twitter4s.entities._
import com.danielasfregola.twitter4s.http.clients.rest.RestClient
import com.danielasfregola.twitter4s.http.clients.rest.friendships.parameters._
import com.danielasfregola.twitter4s.util.Configurations

import scala.concurrent.Future

/** Implements the available requests for the `friendships` resource.
  * */
trait TwitterFriendshipClient extends RestClient with Configurations {

  private val friendshipsUrl = s"$apiTwitterUrl/$twitterVersion/friendships"

  /** Returns a collection of user ids that the currently authenticated user does not want to receive retweets from.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/no_retweets/ids" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/no_retweets/ids</a>.
    *
    * @return : The sequence of user ids the currently authenticated user does not want to receive retweets from.
    * */
  def noRetweetsUserIds(): Future[Seq[Long]] = {
    val parameters = BlockedParameters(stringify_ids = false)
    genericGetNoRetweetsUserIds[Seq[Long]](parameters)
  }

  @deprecated("use noRetweetsUserIds instead", "2.2")
  def getNoRetweetsUserIds(): Future[Seq[Long]]  =
    noRetweetsUserIds()

  /** Returns a collection of user stringified ids that the currently authenticated user does not want to receive retweets from.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/no_retweets/ids" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/no_retweets/ids</a>.
    *
    * @return : The sequence of the user stringified ids the currently authenticated user does not want to receive retweets from.
    * */
  def noRetweetsUserStringifiedIds(): Future[Seq[String]] = {
    val parameters = BlockedParameters(stringify_ids = true)
    genericGetNoRetweetsUserIds[Seq[String]](parameters)
  }

  @deprecated("use noRetweetsUserStringifiedIds instead", "2.2")
  def getNoRetweetsUserStringifiedIds(): Future[Seq[String]] =
    noRetweetsUserStringifiedIds()

  private def genericGetNoRetweetsUserIds[T: Manifest](parameters: BlockedParameters): Future[T] =
    Get(s"$friendshipsUrl/no_retweets/ids.json", parameters).respondAs[T]

  /** Returns a collection of numeric IDs for every user who has a pending request to follow the authenticating user.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/incoming" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/incoming</a>.
    *
    * @param cursor : By default it is `-1`,  which is the first “page”.
    *               Causes the list of blocked users to be broken into pages of no more than 5000 IDs at a time.
    *               The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried.
    * @return : The sequence of the user ids that have a pending request to follow the authenticating user.
    * */
  def incomingFriendshipIds(cursor: Long = -1): Future[UserIds] = {
    val parameters = FriendshipParameters(cursor, stringify_ids = false)
    genericGetIncomingFriendships[UserIds](parameters)
  }

  @deprecated("use incomingFriendshipIds instead", "2.2")
  def getIncomingFriendshipIds(cursor: Long = -1): Future[UserIds] =
    incomingFriendshipIds(cursor)

  /** Returns a collection of numeric stringified IDs for every user who has a pending request to follow the authenticating user.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/incoming" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/incoming</a>.
    *
    * @param cursor : By default it is `-1`,  which is the first “page”.
    *               Causes the list of blocked users to be broken into pages of no more than 5000 IDs at a time.
    *               The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried.
    * @return :  The sequence of the user stringified ids that have a pending request to follow the authenticating user.
    * */
  def incomingFriendshipStringifiedIds(cursor: Long = -1): Future[UserStringifiedIds] = {
    val parameters = FriendshipParameters(cursor, stringify_ids = true)
    genericGetIncomingFriendships[UserStringifiedIds](parameters)
  }

  @deprecated("use incomingFriendshipStringifiedIds instead", "2.2")
  def getIncomingFriendshipStringifiedIds(cursor: Long = -1): Future[UserStringifiedIds] =
    incomingFriendshipStringifiedIds(cursor)

  private def genericGetIncomingFriendships[T: Manifest](parameters: FriendshipParameters): Future[T] =
    Get(s"$friendshipsUrl/incoming.json", parameters).respondAs[T]

  /** Returns a collection of numeric IDs for every protected user for whom the authenticating user has a pending follow request.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/outgoing" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/outgoing</a>.
    *
    * @param cursor : By default it is `-1`,  which is the first “page”.
    *               Causes the list of blocked users to be broken into pages of no more than 5000 IDs at a time.
    *               The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried.
    * @return :  The sequence of the user ids that have a pending follow request from the authenticating user.
    * */
  def outgoingFriendshipIds(cursor: Long = -1): Future[UserIds] = {
    val parameters = FriendshipParameters(cursor, stringify_ids = false)
    genericOutgoingFriendships[UserIds](parameters)
  }

  @deprecated("use outgoingFriendshipIds instead", "2.2")
  def getOutgoingFriendshipIds(cursor: Long = -1): Future[UserIds] =
    outgoingFriendshipIds(cursor)

  /** Returns a collection of numeric stringified IDs for every protected user for whom the authenticating user has a pending follow request.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/outgoing" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/outgoing</a>.
    *
    * @param cursor : By default it is `-1`,  which is the first “page”.
    *               Causes the list of blocked users to be broken into pages of no more than 5000 IDs at a time.
    *               The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried.
    * @return :  The sequence of the user stringified ids that have a pending follow request from the authenticating user.
    * */
  def outgoingFriendshipStringifiedIds(cursor: Long = -1): Future[UserStringifiedIds] = {
    val parameters = FriendshipParameters(cursor, stringify_ids = true)
    genericOutgoingFriendships[UserStringifiedIds](parameters)
  }

  @deprecated("use outgoingFriendshipStringifiedIds instead", "2.2")
  def getOutgoingFriendshipStringifiedIds(cursor: Long = -1): Future[UserStringifiedIds] =
    outgoingFriendshipStringifiedIds(cursor)

  private def genericOutgoingFriendships[T: Manifest](parameters: FriendshipParameters): Future[T] =
    Get(s"$friendshipsUrl/outgoing.json", parameters).respondAs[T]

  /** Allows the authenticating users to follow the specified user id.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/create" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/create</a>.
    *
    * @param user_id : The ID of the user for whom to befriend.
    *                Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @param notify : By default it is `true`.
    *               Enable notifications for the target user.
    * @return :  The user representation of the target user.
    * */
  def followUserId(user_id: Long, notify: Boolean = true): Future[User] = {
    val parameters = FollowParameters(Some(user_id), screen_name = None, notify)
    genericFollow(parameters)
  }

  /** Allows the authenticating users to follow the specified user.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/create" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/create</a>.
    *
    * @param screen_name : The screen name of the user for whom to befriend.
    *                    Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @param notify : By default it is `true`.
    *               Enable notifications for the target user.
    * @return :  The user representation of the target user.
    * */
  def followUser(screen_name: String, notify: Boolean = true): Future[User] = {
    val parameters = FollowParameters(user_id = None, Some(screen_name), notify)
    genericFollow(parameters)
  }

  private def genericFollow(parameters: FollowParameters): Future[User] =
    Post(s"$friendshipsUrl/create.json", parameters).respondAs[User]

  /** Allows the authenticating users to unfollow the specified user id.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/destroy" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/destroy</a>.
    *
    * @param user_id : The ID of the user for whom to unfollow.
    *                Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def unfollowUserId(user_id: Long): Future[User] = {
    val parameters: UnfollowParameters = UnfollowParameters(Some(user_id), screen_name = None)
    genericUnfollow(parameters)
  }

  /** Allows the authenticating users to unfollow the specified user.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/destroy" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/destroy</a>.
    *
    * @param screen_name : The screen name of the user for whom to unfollow.
    *                    Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def unfollowUser(screen_name: String): Future[User] = {
    val parameters: UnfollowParameters = UnfollowParameters(user_id = None, Some(screen_name))
    genericUnfollow(parameters)
  }

  private def genericUnfollow(parameters: UnfollowParameters): Future[User] =
    Post(s"$friendshipsUrl/destroy.json", parameters).respondAs[User]

  /** Allows one to enable retweets from the specified user.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/update" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/update</a>.
    *
    * @param screen_name : The screen name of the user for whom to befriend.
    *                    Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def enableRetweetsNotificationsForUser(screen_name: String): Future[Relationship] = {
    val parameters = RetweetNotificationParameters(user_id = None, Some(screen_name), retweets = true)
    genericNotifications(parameters)
  }

  /** Allows one to enable retweets from the specified user id.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/update" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/update</a>.
    *
    * @param user_id : The ID of the user for whom to befriend.
    *                Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def enableRetweetsNotificationsForUserId(user_id: Long): Future[Relationship] = {
    val parameters = RetweetNotificationParameters(Some(user_id), screen_name = None, retweets = true)
    genericNotifications(parameters)
  }

  /** Allows one to disable retweets from the specified user.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/update" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/update</a>.
    *
    * @param screen_name : The screen name of the user for whom to befriend.
    *                    Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def disableRetweetsNotificationsForUser(screen_name: String): Future[Relationship] = {
    val parameters = RetweetNotificationParameters(user_id = None, Some(screen_name), retweets = false)
    genericNotifications(parameters)
  }

  /** Allows one to disable retweets from the specified user id.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/update" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/update</a>.
    *
    * @param user_id : The ID of the user for whom to befriend.
    *                Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def disableRetweetsNotificationsForUserId(user_id: Long): Future[Relationship] = {
    val parameters = RetweetNotificationParameters(Some(user_id), screen_name = None, retweets = false)
    genericNotifications(parameters)
  }

  /** Allows one to enable device notifications from the specified user.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/update" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/update</a>.
    *
    * @param screen_name : The screen name of the user for whom to befriend.
    *                    Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def enableDeviceNotificationsForUser(screen_name: String): Future[Relationship] = {
    val parameters = DeviceNotificationParameters(user_id = None, Some(screen_name), device = true)
    genericNotifications(parameters)
  }

  /** Allows one to enable device notifications from the specified user id.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/update" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/update</a>.
    *
    * @param user_id : The ID of the user for whom to befriend.
    *                Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def enableDeviceNotificationsForUserId(user_id: Long): Future[Relationship] = {
    val parameters = DeviceNotificationParameters(Some(user_id), screen_name = None, device = true)
    genericNotifications(parameters)
  }

  /** Allows one to disable device notifications from the specified user.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/update" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/update</a>.
    *
    * @param screen_name : The screen name of the user for whom to befriend.
    *                    Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def disableDeviceNotificationsForUser(screen_name: String): Future[Relationship] = {
    val parameters = DeviceNotificationParameters(user_id = None, Some(screen_name), device = false)
    genericNotifications(parameters)
  }

  /** Allows one to disable device notifications from the specified user id.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/post/friendships/update" target="_blank">
    *   https://dev.twitter.com/rest/reference/post/friendships/update</a>.
    *
    * @param user_id : The ID of the user for whom to befriend.
    *                Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The user representation of the target user.
    * */
  def disableDeviceNotificationsForUserId(user_id: Long): Future[Relationship] = {
    val parameters = DeviceNotificationParameters(Some(user_id), screen_name = None, device = false)
    genericNotifications(parameters)
  }

  private def genericNotifications(parameters: NotificationParameters): Future[Relationship] =
    Post(s"$friendshipsUrl/update.json", parameters).respondAs[Relationship]

  /** Returns detailed information about the relationship between two arbitrary users ids.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/show" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/show</a>.
    *
    * @param source_id : The user id of the subject user.
    * @param target_id : The user id of the target user.
    * @return :  The representation of the relationship between the two users.
    * */
  def relationshipBetweenUserIds(source_id: Long, target_id: Long): Future[Relationship] = {
    val parameters = RelationshipParametersByIds(source_id, target_id)
    genericGetRelationship(parameters)
  }

  @deprecated("use relationshipBetweenUserIds instead", "2.2")
  def getRelationshipBetweenUserIds(source_id: Long, target_id: Long): Future[Relationship] =
    relationshipBetweenUserIds(source_id, target_id)

  /** Returns detailed information about the relationship between two arbitrary users.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/show" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/show</a>.
    *
    * @param source_screen_name : The screen name of the subject user.
    * @param target_screen_name : The screen name of the target user.
    * @return :  The representation of the relationship between the two users.
    * */
  def relationshipBetweenUsers(source_screen_name: String, target_screen_name: String): Future[Relationship] = {
    val parameters = RelationshipParametersByNames(source_screen_name, target_screen_name)
    genericGetRelationship(parameters)
  }

  @deprecated("use relationshipBetweenUsers instead", "2.2")
  def getRelationshipBetweenUsers(source_screen_name: String, target_screen_name: String): Future[Relationship] =
    relationshipBetweenUsers(source_screen_name, target_screen_name)

  private def genericGetRelationship(parameters: RelationshipParameters): Future[Relationship] =
    Get(s"$friendshipsUrl/show.json", parameters).respondAs[Relationship]

  /** Returns the relationships of the authenticating user of up to 100 user screen names.
    * Values for connections can be: `following`, `following_requested`, `followed_by`, `none`, `blocking`, `muting`.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/lookup" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/lookup</a>.
    *
    * @param screen_names :  The list of screen names.
    *                     At least 1 screen name needs to be provided. Up to 100 are allowed in a single request.
    *                     Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The sequence of the lookup relationships.
    * */
  def relationshipsWithUsers(screen_names: String*): Future[Seq[LookupRelationship]] = {
    require(!screen_names.isEmpty, "please, provide at least one screen name")
    val parameters = RelationshipsParameters(user_id = None, screen_name = Some(screen_names.mkString(",")))
    genericGetRelationships(parameters)
  }

  @deprecated("use relationshipsWithUsers instead", "2.2")
  def getRelationshipsWithUsers(screen_names: String*): Future[Seq[LookupRelationship]] =
    relationshipsWithUsers(screen_names:_*)

  /** Returns the relationships of the authenticating user of up to 100 user ids.
    * Values for connections can be: `following`, `following_requested`, `followed_by`, `none`, `blocking`, `muting`.
    * For more information see
    * <a href="https://dev.twitter.com/rest/reference/get/friendships/lookup" target="_blank">
    *   https://dev.twitter.com/rest/reference/get/friendships/lookup</a>.
    *
    * @param user_ids :  The list of user ids.
    *                 At least 1 user id needs to be provided. Up to 100 are allowed in a single request.
    *                 Helpful for disambiguating when a valid user ID is also a valid screen name.
    * @return :  The sequence of the lookup relationships.
    * */
  def relationshipsWithUserIds(user_ids: Long*): Future[Seq[LookupRelationship]] = {
    require(!user_ids.isEmpty, "please, provide at least one user id")
    val parameters = RelationshipsParameters(user_id = Some(user_ids.mkString(",")), screen_name = None)
    genericGetRelationships(parameters)
  }

  @deprecated("use relationshipsWithUserIds instead", "2.2")
  def getRelationshipsWithUserIds(user_ids: Long*): Future[Seq[LookupRelationship]] =
    relationshipsWithUserIds(user_ids:_*)

  private def genericGetRelationships(parameters: RelationshipsParameters): Future[Seq[LookupRelationship]] =
    Get(s"$friendshipsUrl/lookup.json", parameters).respondAs[Seq[LookupRelationship]]
}
