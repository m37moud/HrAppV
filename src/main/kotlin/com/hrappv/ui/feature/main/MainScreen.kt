package com.hrappv.ui.feature.main

import androidx.compose.animation.animateContentSize
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrappv.App
import com.hrappv.ui.security.UserAuthSate
import com.hrappv.ui.value.HrAppVTheme
import com.hrappv.ui.value.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.SignOutAlt
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import com.arkivanov.decompose.Child
import com.hrappv.ui.components.AppMenuHeader
import com.hrappv.ui.components.NavItem
import com.hrappv.ui.components.NavigationMenuItem
import com.hrappv.ui.feature.EmployeResult.EmployResultScreenComponent
import com.hrappv.ui.feature.about.AboutComponent
import com.hrappv.ui.feature.add_employe.AddEmployeScreenComponent
import com.hrappv.ui.feature.home_screen.HomeComponent
import com.hrappv.ui.feature.settings.SettingsComponent
import com.hrappv.ui.navigation.Component
import com.hrappv.ui.navigation.Config
import com.hrappv.ui.navigation.NavHostComponent
import com.hrappv.ui.navigation.RootComponent


@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val welcomeText by viewModel.welcomeText.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = welcomeText,
                style = MaterialTheme.typography.h3
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Button(
                onClick = {
                    viewModel.onClickMeClicked()
                }
            ) {
                Text(text = R.string.ACTION_MAIN_CLICK_ME)
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Button(
                onClick = {
                    viewModel.startEmpResultScreen()
                }
            ) {
                Text(text = R.string.ACTION_EMPLOYEE_RESULT)
            }
        }
    }
}

@Preview
@Composable
fun MainScreen2(
    modifier: Modifier = Modifier,
//    viewModel: MainViewModel,
//    component: RootComponent,
//    loginViewModel: LoginViewModel,
    userAuthSate: UserAuthSate = UserAuthSate(),
    activeComponent: Component,
    onNavIconClick: () -> Unit,
    onHomeClick: () -> Unit,
    onAddEmployeeClick: () -> Unit,
    onViewEmployeesClick: () -> Unit,
    onEmployeeResultClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onAboutClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState(0)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
//    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    var isMenuPressed by remember { mutableStateOf(false) }
//    val navMenu = modifier.weight(0.2f)
//    val navContent = modifier.weight(0.8f)
//    Surface(
//        modifier = modifier
//            .fillMaxSize()
//            .background(Color.LightGray)
//    , elevation = 6.dp
//    ) {

//    Scaffold(
//        modifier
////            .animateContentSize(
////                animationSpec = spring(
////                    dampingRatio = Spring.DampingRatioHighBouncy,
////                    stiffness = Spring.StiffnessHigh
////                )
////            )
//        , scaffoldState = scaffoldState,
////        topBar = {
////            MyTopAppBar {
////                coroutineScope.launch {
//////                    scaffoldState.drawerState.open()
////                }
////            }
////        },
////        drawerContent = {
////            DrawerContent { itemLabel ->
////
////                coroutineScope.launch {
////                    // delay for the ripple effect
////                    delay(timeMillis = 250)
////                    scaffoldState.drawerState.close()
////                }
////            }
////        }
//
////        drawerContent = {
////            Text("Drawer title", modifier = Modifier.padding(16.dp))
////            Divider()
////
////            // Drawer items
////        }
//        drawerGesturesEnabled = false
//    ) {

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        NavMenu(
//                modifier = Modifier
//                    .weight(0.15f),
            isMenuPressed = isMenuPressed,
            activeComponent = activeComponent,
            onNavIconClick = {
                coroutineScope.launch {
//                    scaffoldState.drawerState.open()
                    isMenuPressed = !isMenuPressed


                }
            },
            onHomeClick = onHomeClick,
            onAddEmployeeClick = onAddEmployeeClick,
            onViewEmployeesClick = onViewEmployeesClick,
            onEmployeeResultClick = onEmployeeResultClick,
            onSettingsClick = onSettingsClick,
            onAboutClick = onAboutClick,
        )

        Box(
//                modifier = Modifier.fillMaxHeight()
//                    .weight(0.85f)
        ) {
            Surface {

                content()
            }
        }
    }

//    }

}

@Composable
private fun MyTopAppBar(onNavIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "SemicolonSpace") },
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavIconClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Open Navigation Drawer"
                )
            }
        }
    )
}

@Composable
private fun DrawerContent(
    gradientColors: List<Color> = listOf(Color(0xFFF70A74), Color(0xFFF59118)),
    itemClick: (String) -> Unit
) {

    val itemsList = prepareNavigationDrawerItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = gradientColors)),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 36.dp)
    ) {

        item {

            // user's image
            Image(
                modifier = Modifier
                    .size(size = 120.dp)
                    .clip(shape = CircleShape),
                painter = painterResource("drawables/logo.png"),
                contentDescription = "Profile Image"
            )

            // user's name
            Text(
                modifier = Modifier
                    .padding(top = 12.dp),
                text = "Hermione",
                fontSize = 26.sp,
//                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            // user's email
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
                text = "hermione@email.com",
//                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        items(itemsList) { item ->
            NavigationListItem(item = item) {
                itemClick(item.label)
            }
        }
    }
}


@Composable
private fun NavigationListItem(
    item: NavigationDrawerItem,
    unreadBubbleColor: Color = Color(0xFF0FFF93),
    itemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                itemClick()
            }
            .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // icon and unread bubble
        Box {

            Icon(
                modifier = Modifier
                    .padding(all = if (item.showUnreadBubble && item.label == "Messages") 5.dp else 2.dp)
                    .size(size = if (item.showUnreadBubble && item.label == "Messages") 24.dp else 28.dp),
                imageVector = item.image,
                contentDescription = null,
                tint = Color.White
            )

            // unread bubble
            if (item.showUnreadBubble) {
                Box(
                    modifier = Modifier
                        .size(size = 8.dp)
                        .align(alignment = Alignment.TopEnd)
                        .background(color = unreadBubbleColor, shape = CircleShape)
                )
            }
        }

        // label
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = item.label,
            fontSize = 20.sp,
//            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}

@Composable
private fun prepareNavigationDrawerItems(): List<NavigationDrawerItem> {
    val itemsList = arrayListOf<NavigationDrawerItem>()

    itemsList.add(
        NavigationDrawerItem(
            image = Icons.Default.Home,
            label = "Home"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = Icons.Default.Person,
            label = "Add Employee",
            showUnreadBubble = true
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = Icons.Default.Edit,
            label = "Register Attends",
            showUnreadBubble = true
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = Icons.Default.Settings,
            label = "Settings"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = Icons.Default.Info,
            label = "About"
        )
    )
//
//    itemsList.add(
//        NavigationDrawerItem(
//            image = painterResource("drawables/logo.png"),
//            label = "Settings"
//        )
//    )

    itemsList.add(
        NavigationDrawerItem(
            image = Icons.TwoTone.AccountCircle,
            label = "Logout"
        )
    )

    return itemsList
}

data class NavigationDrawerItem(
    val image: ImageVector,
    val label: String,
    val showUnreadBubble: Boolean = false
)


@Composable
fun NavMenu(
    modifier: Modifier = Modifier,
//    component: Component,
    isMenuPressed: Boolean,
    activeComponent: Component,
//    loginViewModel: LoginViewModel,
    onNavIconClick: () -> Unit,
    onHomeClick: () -> Unit,
    onAddEmployeeClick: () -> Unit,
    onViewEmployeesClick: () -> Unit,

    onEmployeeResultClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onAboutClick: () -> Unit,

    ) {
//    var isMenuPressed by remember { mutableStateOf(true) }
    NavigationRail(
        modifier = modifier
//            .border(shape = RectangleShape, border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary)).padding(4.dp)

            .fillMaxHeight().padding(end = 6.dp)
//            .animateContentSize()
        ,
        elevation = 10.dp,
        header = {
            Row(
                modifier = modifier
//                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {

                if (isMenuPressed) {
                    Text(
                        "${App.appArgs.appName} ",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.subtitle1,
                    )

                    Spacer(modifier = modifier.width(120.dp))
                } else {
                    Spacer(modifier = modifier.width(8.dp))
                }

                Icon(
                    imageVector = Icons.Default.Menu,
                    modifier = Modifier.size(25.dp).clickable {
                        onNavIconClick()
                    },
                    contentDescription = null,
//                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                    tint = MaterialTheme.colors.onPrimary

                )
                Spacer(modifier = Modifier.width(8.dp))


            }
        },
        content = {
//        Card(
//            modifier
//                .animateContentSize(),
//            elevation = 6.dp,
//        ) {

            AppMenuHeader()

            Spacer(
                modifier = Modifier
                    .height(10.dp)
//                .fillMaxWidth()
                    .background(color = Color.DarkGray)
            )

            Column(
                modifier = modifier
                    .fillMaxHeight()
//                    .padding(top = 16.dp)
//                .border(shape = RectangleShape, border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary))

            ) {


//                Spacer(modifier = modifier.height(16.dp))
                NavigationMenuItem(
                    selected = activeComponent is HomeComponent,
                    modifier = modifier,
                    icon = Icons.Default.Home,
                    label = "Home",
                    isMenuPressed = isMenuPressed
//                component.onHomeTabClicked()
                    ,
                    onClick = { onHomeClick() }
                )


                Spacer(modifier = modifier.height(8.dp))
                NavigationMenuItem(
                    selected = activeComponent is AddEmployeScreenComponent,

                    modifier = modifier,
                    icon = Icons.Default.Person,
                    label = "Add Employee",
                    isMenuPressed = isMenuPressed,
                    onClick = { onAddEmployeeClick() }
                )

                Spacer(modifier = modifier.height(8.dp))
                NavigationMenuItem(
                    selected = activeComponent is AddEmployeScreenComponent,

                    modifier = modifier,
                    icon = Icons.Default.Person,
                    label = "Employees",
                    isMenuPressed = isMenuPressed,
                    onClick = { onViewEmployeesClick() }
                )

                Spacer(modifier = modifier.height(8.dp))
                NavigationMenuItem(
                    selected = activeComponent is EmployResultScreenComponent,

                    modifier = modifier,
                    icon = Icons.Default.Edit,
                    label = "Register Attends",
                    isMenuPressed = isMenuPressed,
                    onClick = { onEmployeeResultClick() }

                )

                Spacer(modifier = modifier.height(8.dp))
                NavigationMenuItem(
                    selected = activeComponent is SettingsComponent,

                    modifier = modifier,
                    icon = Icons.Default.Settings,
                    label = "Settings",
                    isMenuPressed = isMenuPressed,
                    onClick = { onSettingsClick() }
                )
                Spacer(modifier = modifier.height(8.dp))
                NavigationMenuItem(
                    selected = activeComponent is AboutComponent,

                    modifier = modifier,
                    icon = Icons.Default.Info,
                    label = "About",
                    isMenuPressed = isMenuPressed,
                    onClick = { onAboutClick() })

                Spacer(modifier = modifier.height(8.dp))
                NavigationMenuItem(
                    modifier = modifier,
                    icon = FontAwesomeIcons.Solid.SignOutAlt,
                    label = "Log Out",
                    isMenuPressed = isMenuPressed, onClick = {}
                )


            }
//        }

        }
    )

}


//@Composable
//fun spotifyGradient() = LinearGradient(spotifyGradient, startX = 0f, endX = 0f, startY = 0f, endY = 100f)
//


@Composable
@Preview
fun FavoriteCollectionCardPreview() {
    val darkTheme by remember { mutableStateOf(false) }
    HrAppVTheme(darkTheme) {

    }
}

